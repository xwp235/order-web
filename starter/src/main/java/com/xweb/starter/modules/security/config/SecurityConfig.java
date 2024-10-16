package com.xweb.starter.modules.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.common.filter.RequestLogRecordFilter;
import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.modules.security.config.apply.ApplySecurityConfig;
import com.xweb.starter.modules.security.config.authorization.CompositeAuthorizationManager;
import com.xweb.starter.modules.security.config.filter.CheckImageCodeFilter;
import com.xweb.starter.modules.security.config.filter.CheckLoginStateFilter;
import com.xweb.starter.modules.security.config.handler.WebLogoutSuccessHandler;
import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import com.xweb.starter.modules.security.config.strategy.SimpleCompositeInvalidSessionStrategy;
import com.xweb.starter.modules.security.config.strategy.SimpleCompositeSessionInformationExpiredStrategy;
import com.xweb.starter.modules.security.dao.HisClientLoginLogDao;
import com.xweb.starter.modules.security.dao.RoleDao;
import com.xweb.starter.utils.JsonUtil;
import com.xweb.starter.utils.RequestUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.context.*;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * spring-security核心配置类
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${server.servlet.session.cookie.name}")
    private String cookieName;
    private final SecurityProperties securityProperties;

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository,
            ApplicationContext applicationContext,
            PermissionMetadataSource metadataSource,
            RequestLogRecordFilter requestLogRecordFilter,
            CheckLoginStateFilter checkLoginStateFilter,
            CheckImageCodeFilter checkImageCodeFilter,
            ObjectMapper objectMapper,
            HisClientLoginLogDao clientLoginLogDao,
            PersistentTokenRepository persistentTokenRepository
    ) throws Exception {

        var authorizationFilter = new AuthorizationFilter(new CompositeAuthorizationManager(metadataSource));
        authorizationFilter.setAuthorizationEventPublisher(new SpringAuthorizationEventPublisher(applicationContext));
        authorizationFilter.setSecurityContextHolderStrategy(SecurityContextHolder.getContextHolderStrategy());

        http
            // 禁止匿名用户登录
            .anonymous(AnonymousConfigurer::disable)
            // 默认的请求缓存机制 防止未经授权的用户被重定向到原始请求URL，而是在登录或认证后直接跳转到默认页面。
            .requestCache(cache-> cache.requestCache(new NullRequestCache()))
            .securityContext((securityContext) -> securityContext
                    // 指定了 SecurityContext 的存储库
                    .securityContextRepository(securityContextRepository)
                    // 设置 requireExplicitSave(true) 后，SecurityContext 的更改不会自动保存到 SecurityContextRepository 中，只有显式调用保存方法时才会生效
                    .requireExplicitSave(true)
            )
            // http-basic配置,配置为不开启
            .httpBasic(HttpBasicConfigurer::disable)
            // 防止csrf攻击配置
            .csrf(csrf-> csrf.ignoringRequestMatchers(securityProperties.getCsrfIgnoreUrlPatterns()))
            // 认证授权路径配置
            .authorizeHttpRequests(requests -> {
                requests.requestMatchers(securityProperties.getWhiteUrlPatterns()).permitAll();
                requests.anyRequest().authenticated();
            })
            // 在安全链开始前加入logback的输出日志对应ID
            .addFilterBefore(requestLogRecordFilter, DisableEncodeUrlFilter.class)
            // 检测图片验证码是否正确
            .addFilterBefore(checkImageCodeFilter, SecurityContextHolderFilter.class)
            // 检测用户是否登录，如果已经登录访问登录页时将其导向到首页
            .addFilterAfter(
                    checkLoginStateFilter,
                    SecurityContextHolderFilter.class
            )
            // 配置自定义授权过滤器
            .addFilterAt(authorizationFilter, AuthorizationFilter.class);

             var maximumSession = securityProperties.getMaximumSessions();
             var compositeInvalidSessionStrategy = new SimpleCompositeInvalidSessionStrategy(securityProperties.getInvalidSessionUrl(),cookieName);
             // session并发登录控制
             http.sessionManagement(session -> {
                session.sessionAuthenticationStrategy(
                   new ChangeSessionIdAuthenticationStrategy()
                )
                // 自定义session无效后的处理策略(ajax访问返回json)
                .invalidSessionStrategy(compositeInvalidSessionStrategy);
                if (Objects.nonNull(maximumSession)) {
                    var compositeSessionExpiredStrategy = new SimpleCompositeSessionInformationExpiredStrategy(securityProperties.getExpiredSessionUrl());
                    session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(maximumSession)
                        .maxSessionsPreventsLogin(securityProperties.getMaxSessionsPreventsLogin())
                            // 自定义并发控制加载时session过期后的处理策略(ajax访问返回json)
                            .expiredSessionStrategy(compositeSessionExpiredStrategy);
                }
             });

            // 自定义AuthenticationManager对象配置
            http.authenticationManager(authenticationManager)
                    .apply(new ApplySecurityConfig(securityProperties, objectMapper,authenticationManager));

            http.exceptionHandling(handling->
                handling
                  // 未登录用户尝试访问需要认证才能访问的资源时的处理策略
                  .authenticationEntryPoint(
                          new WebAuthenticationEntryPoint(securityProperties.getFormLogin().getLoginPage())
                  )
                  // 登录用户尝试访问未授权资源时的拒绝策略
                  .accessDeniedHandler((request,response,ex) -> {
                    if (response.isCommitted()) {
                        return;
                    }
                    if (RequestUtil.isAjaxRequest(request)) {
                        setResponseDetails(response);
                        var result = JsonResp.error(HttpStatus.FORBIDDEN.getReasonPhrase()).setCode(HttpStatus.FORBIDDEN.value());
                        response.getWriter().write(JsonUtil.obj2Json(result));
                        return;
                    }
                    request.getSession().setAttribute(WebAttributes.ACCESS_DENIED_403, ex);
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    request.getRequestDispatcher(securityProperties.getAccessDeniedUrl()).forward(request, response);
                  })
            );

            var rememberMeProperties = securityProperties.getRememberMe();
            if (rememberMeProperties.getEnabled()){
                http.rememberMe(
                  rememberMe -> rememberMe.tokenRepository(persistentTokenRepository)
                          // 默认记住我一周内有效
                          .tokenValiditySeconds(rememberMeProperties.getTokenValiditySeconds())
                          .rememberMeCookieName(rememberMeProperties.getCookieName())
                );
            }

            // 自定义退出登录钩子
            http.logout(logout->
                    logout
                        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES)))
                        .logoutSuccessHandler(new WebLogoutSuccessHandler(clientLoginLogDao))
            );
        return http.build();
    }

    @Bean
    SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Bean
    AuthenticationManager authenticationManager(
            PasswordEncoder passwordEncoder,
            AuthenticationEventPublisher authenticationEventPublisher,
            UserDetailsService daoUserDetailsService,
            GrantedAuthoritiesMapper authoritiesMapper
    ) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setAuthoritiesMapper(authoritiesMapper);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(daoUserDetailsService);
        var providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(true);
        providerManager.setAuthenticationEventPublisher(authenticationEventPublisher);
        return providerManager;
    }

    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(Constants.ROLE_PREFIX);
    }

    @Bean
    static RoleHierarchy roleHierarchy(RoleDao roleDao) {
        var weightRoleList = roleDao.selectRolesOrderByWeightDesc();
        var roleList = weightRoleList.stream().map(role-> role.getMrId().substring(Constants.ROLE_PREFIX.length())).toList();
        RoleHierarchyImpl.Builder roleHierarchy = RoleHierarchyImpl.withRolePrefix(Constants.ROLE_PREFIX);
        // 遍历角色列表，动态构建 implies 关系
        for (int i = 0; i < roleList.size() - 1; i++) {
            String currentRole = roleList.get(i);
            String impliedRole = roleList.get(i + 1);

            // 动态构建权限层级
            roleHierarchy = roleHierarchy.role(currentRole).implies(impliedRole);
        }

        // 完成链式构建，返回 RoleHierarchyImpl
        return roleHierarchy.build();
    }

    @Bean
    GrantedAuthoritiesMapper authoritiesMapper(RoleHierarchy roleHierarchy) {
        return new RoleHierarchyAuthoritiesMapper(roleHierarchy);
    }

    @Bean
    PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // tokenRepository.setCreateTableOnStartup(true);  // Uncomment this line if you want to create the table on startup
        return tokenRepository;
    }

    @Bean
    FilterRegistrationBean<RequestLogRecordFilter> requestLogRecordFilterFilterRegistrationBean(RequestLogRecordFilter filter) {
        var registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    FilterRegistrationBean<CheckLoginStateFilter> checkLoginStateFilterBean(CheckLoginStateFilter filter) {
        var registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    FilterRegistrationBean<CheckImageCodeFilter> checkImageCodeFilterBean(CheckImageCodeFilter filter) {
        var registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    private void setResponseDetails(HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK.value());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
