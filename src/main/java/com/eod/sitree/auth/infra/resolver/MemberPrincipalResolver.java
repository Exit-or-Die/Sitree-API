package com.eod.sitree.auth.infra.resolver;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Member;
import com.eod.sitree.member.domain.model.Provider;
import com.eod.sitree.member.exception.MemberException;
import com.eod.sitree.member.service.MemberService;
import jakarta.annotation.PostConstruct;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberPrincipalResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(MemberPrincipal.class) && parameter.getParameterType().equals(Member.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        try {

            final String authorization = ((ServletWebRequest) webRequest).getRequest().getHeader(JwtTokenType.ACCESS_TOKEN.getHeaderName());
            JwtToken token = new JwtToken(authorization, AuthService.jwtKeypair);
            token.validateToken();

            Member member = memberService.findMember(token.getMemberClaim().getProvider(),
                token.getMemberClaim().getEmail());

            return member;

        } catch (Exception e) {

            log.info("UserInfoResolver token : {}",
                ((ServletWebRequest) webRequest).getRequest().getHeader(JwtTokenType.ACCESS_TOKEN.getHeaderName()));
            log.info("UserInfoResolver path : {}",
                ((ServletWebRequest) webRequest).getRequest().getRequestURI());
            log.info("UserInfoResolver Exception : {}", e.getStackTrace());

            MemberPrincipal memberPrincipalAnnotation = parameter.getParameterAnnotation(MemberPrincipal.class);

            if (memberPrincipalAnnotation != null) {

                throw new MemberException(ApplicationErrorType.MEMBER_NOT_FOUND);
            }

            throw new RuntimeException("User Info Resolver Exception", e);
        }
    }
}