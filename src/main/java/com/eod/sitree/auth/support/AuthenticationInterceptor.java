package com.eod.sitree.auth.support;

import com.eod.sitree.auth.domain.JwtToken;
import com.eod.sitree.auth.domain.JwtTokenType;
import com.eod.sitree.auth.domain.repository.OAuthRepository;
import com.eod.sitree.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final OAuthRepository oAuthRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        if (checkAnnotation(handler, AuthNotRequired.class) || request.getMethod().equals("OPTIONS")) {
            return true;
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