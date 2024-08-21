package com.eod.sitree.config;

import com.eod.sitree.auth.support.AuthenticationInterceptor;
import com.eod.sitree.auth.domain.repository.OAuthRepository;
import com.eod.sitree.auth.infra.resolver.MemberPrincipalResolver;
import com.eod.sitree.auth.support.LocalOnlyInterceptor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final OAuthRepository oAuthRepository;
    private final MemberPrincipalResolver memberPrincipalResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocalOnlyInterceptor())
            .order(1)
            .addPathPatterns("/**");

        registry.addInterceptor(new AuthenticationInterceptor(oAuthRepository))
            .order(2)
            .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberPrincipalResolver);
    }
}
