package com.xweb.starter.modules.security.config;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.modules.security.config.authorization.CompositeAuthorizationManager;
import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import com.xweb.starter.modules.security.config.properties.SecurityProperties;
import com.xweb.starter.modules.security.config.strategy.FormLoginConfig;
import com.xweb.starter.modules.security.dao.RoleDao;
import com.xweb.starter.modules.security.domain.entity.MastRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * spring-security核心配置类
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityProperties securityProperties;

    @Bean
    SecurityFilterChain filterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository,
            ApplicationContext applicationContext,
            PermissionMetadataSource metadataSource,
            GrantedAuthoritiesMapper authoritiesMapper
    ) throws Exception {

        var authorizationFilter = new AuthorizationFilter(new CompositeAuthorizationManager(metadataSource,authoritiesMapper));
        authorizationFilter.setAuthorizationEventPublisher(new SpringAuthorizationEventPublisher(applicationContext));
        authorizationFilter.setSecurityContextHolderStrategy(SecurityContextHolder.getContextHolderStrategy());

        http
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
                // todo 动态加载角色可访问的页面间的对应关系
//                for (AuthorizationConfigService.AuthorizationRule rule : configService.loadAuthorizationRules()) {
//                    requests.requestMatchers(rule.getPath()).hasRole(rule.getRole());
//                }
                requests.anyRequest().authenticated();
            })
            // 配置自定义授权过滤器
            .addFilterBefore(authorizationFilter, AuthorizationFilter.class)
            // session并发登录控制
            .sessionManagement(session->
                session
                    .sessionAuthenticationStrategy(
                            new ChangeSessionIdAuthenticationStrategy()
                    )
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
//                                .expiredSessionStrategy()
            )
            // 自定义AuthenticationManager对象配置
            .authenticationManager(authenticationManager)
            // form表单登录方式配置
            .apply(new FormLoginConfig(securityProperties));
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
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(Constants.ROLE_PREFIX);
    }

    @Bean
    static RoleHierarchy roleHierarchy(RoleDao roleDao) {
        var weightRoleList = roleDao.selectRolesOrderByWeightDesc();
        var roleList = weightRoleList.stream().map(MastRole::getMrId).toList();

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

}
