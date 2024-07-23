package com.eod.sitree.auth;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.domain.repository.OAuthRepository;
import com.eod.sitree.auth.exception.AuthException;
import com.eod.sitree.auth.service.AuthService;
import com.eod.sitree.auth.support.AuthNotRequired;
import com.eod.sitree.auth.support.OAuthRequired;
import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.member.domain.model.Provider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final OAuthRepository oAuthRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        if (checkAnnotation(handler, AuthNotRequired.class)) {
            return true;
        }

        if (checkAnnotation(handler, OAuthRequired.class)) {

            String provider = Optional.ofNullable(request.getHeader("x-oauth-provider"))
                .orElseThrow(() -> new AuthException(ApplicationErrorType.UNAUTHORIZED));
            String oAuthToken = Optional.ofNullable(request.getHeader("x-oauth-token"))
                .orElseThrow(() -> new AuthException(ApplicationErrorType.UNAUTHORIZED));

            if (Provider.GOOGLE.name().equalsIgnoreCase(provider)
                && oAuthRepository.validateGoogleToken(oAuthToken).getStatusCode().is2xxSuccessful()) {

                return HandlerInterceptor.super.preHandle(request, response, handler);
            }

            if (Provider.GITHUB.name().equalsIgnoreCase(provider)
                && oAuthRepository.validateGithubToken(oAuthToken).getStatusCode().is2xxSuccessful()) {

                return HandlerInterceptor.super.preHandle(request, response, handler);
            }

            throw new AuthException(ApplicationErrorType.UNAUTHORIZED);
        }

        String token = request.getHeader(JwtTokenType.ACCESS_TOKEN.getHeaderName());
        JwtToken jwtToken = new JwtToken(token, AuthService.jwtKeypair);
        jwtToken.validateToken();

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean checkAnnotation(Object handler, Class cls) {
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            return handlerMethod.getMethod().getDeclaringClass().isAnnotationPresent(cls)
                || handlerMethod.getMethodAnnotation(cls) != null;
        } catch (Exception e) {

            return false;
        }
    }
}
