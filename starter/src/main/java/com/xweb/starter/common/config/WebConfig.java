package com.xweb.starter.common.config;

import com.xweb.starter.modules.security.dao.MenuDao;
import com.xweb.starter.modules.security.interceptor.ViewRenderedInterceptor;
import com.xweb.starter.modules.security.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final PermissionService permissionService;
    private final MenuDao menuDao;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ViewRenderedInterceptor(permissionService))
                .addPathPatterns(menuDao.needAuthenticationUrlPath());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(false);
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("backend/login");
        registry.addViewController("/session-expired").setViewName("session-expired");
        registry.addViewController("/invalid-session").setViewName("invalid-session");
        registry.addViewController("/too-many-requests").setViewName("too-many-requests");
        registry.addViewController("/").setViewName("invalid-session");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/404").setViewName("404");
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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        // 允许特定的域名（可以使用 "*" 允许所有域名）
        configuration.setAllowedOrigins(List.of(CorsConfiguration.ALL));
        // 允许的 HTTP 方法，如 GET、POST 等
        configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
        // 允许的请求头（可以使用 "*" 允许所有头部）
        configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
        // 是否允许发送 Cookie
        configuration.setAllowCredentials(true);
        // 一小时内不再需要预检（发送OPTIONS请求）
        configuration.setMaxAge(3600L);
        // 配置路径匹配策略
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }


//    @Override
//    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        resolvers.removeLast();
//        resolvers.add(new DefaultHandlerExceptionResolver(){
//            @Override
//            protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
//                return new ModelAndView((model, request1, response1) -> {
//                   response1.getWriter().println(ex.getBody());
//                });
//            }
//        });
//    }

}
