package com.eod.sitree.auth.support;

import com.eod.sitree.auth.exception.AuthException;
import com.eod.sitree.common.exception.ApplicationErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class LocalOnlyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        if (checkAnnotation(handler, LocalOnly.class) && !Objects.equals(System.getProperty("spring.profiles.active"), "local")) {

            throw new AuthException(ApplicationErrorType.FORBIDDEN, HttpStatus.FORBIDDEN);
        }

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
