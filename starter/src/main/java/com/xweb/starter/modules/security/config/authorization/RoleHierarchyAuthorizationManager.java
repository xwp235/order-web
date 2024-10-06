package com.xweb.starter.modules.security.config.authorization;

import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 层级角色授权管理器
 */
@RequiredArgsConstructor
public class RoleHierarchyAuthorizationManager implements AuthorizationManager<HttpServletRequest> {

    private final PermissionMetadataSource permissionMetadataSource;
    private final GrantedAuthoritiesMapper authoritiesMapper;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, HttpServletRequest request) {
        var requiredAttributes = permissionMetadataSource.getAttributes(request);
        // 如果访问的url没有授权的需求则直接跳过
        if (CollectionUtils.isEmpty(requiredAttributes)) {
            return new AuthorizationDecision(true);
        }
        // 取得用户登录后相关的权限信誉与requiredAttributes里的信息做对比
        var authentication = authenticationSupplier.get();

        // 获取当前用户的权限集合，并应用 RoleHierarchyAuthoritiesMapper
        Collection<? extends GrantedAuthority> userAuthorities = authoritiesMapper.mapAuthorities(authentication.getAuthorities());

        // 检查用户是否拥有请求所需的权限
        boolean hasPermission = requiredAttributes.stream()
                .anyMatch(attr -> userAuthorities.stream()
                        .anyMatch(auth -> auth.getAuthority().equals(attr.getAttribute())));

        return new AuthorizationDecision(hasPermission);
    }

}
