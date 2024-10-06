package com.xweb.starter.modules.security.config.authorization;

import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.function.Supplier;

/**
 * todo 根据不同路径使用不同授权策略
 */
public class CompositeAuthorizationManager implements AuthorizationManager<HttpServletRequest> {

    private final RoleHierarchyAuthorizationManager roleHierarchyAuthorizationManager;

    public CompositeAuthorizationManager(PermissionMetadataSource metadataSource, GrantedAuthoritiesMapper authoritiesMapper) {
        this.roleHierarchyAuthorizationManager = new RoleHierarchyAuthorizationManager(metadataSource,authoritiesMapper);
    }

    private final AntPathRequestMatcher pathMatcher = new AntPathRequestMatcher("/**");

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest request) {
        if (pathMatcher.matches(request)) {
            return roleHierarchyAuthorizationManager.check(authentication, request);
        }
        return new AuthorizationDecision(false);
    }

}
