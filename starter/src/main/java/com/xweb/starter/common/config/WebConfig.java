package com.xweb.starter.common.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/dashboard").setViewName("index");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 所有api接口都应用此配置
        registry.addMapping("/**")
                // 允许所有来源
                .allowedOriginPatterns(CorsConfiguration.ALL)
                // 允许所有header
                .allowedHeaders(CorsConfiguration.ALL)
                // 允许所有请求方式(GET,POST,...)
                .allowedMethods(CorsConfiguration.ALL)
                // 允许请求带上cookie
                .allowCredentials(true)
                // 一小时内不再需要预检（发送OPTIONS请求）
                .maxAge(3600);
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.removeLast();
        resolvers.add(new DefaultHandlerExceptionResolver(){
            @Override
            protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
                return new ModelAndView((model, request1, response1) -> {
                   response1.getWriter().println(ex.getBody());
                });
            }
        });
    }

}
