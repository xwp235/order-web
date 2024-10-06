package com.xweb.starter.modules.security.config.metadatasource;

import com.xweb.starter.modules.security.dao.AccountDao;
import com.xweb.starter.modules.security.helpers.SecurityHelper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PermissionMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final AccountDao accountDao;
    private Map<String, Collection<ConfigAttribute>> permissionMap;

    @PostConstruct
    public void loadBtnPermissions() {
       permissionMap = accountDao.loadButtonPermissions();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        var request = (HttpServletRequest) object;
        // 从权限服务中加载 URL 对应的权限
        return permissionMap.get(SecurityHelper.generateBtnPermissionMapKey(request.getMethod(),request.getRequestURI()));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return permissionMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
