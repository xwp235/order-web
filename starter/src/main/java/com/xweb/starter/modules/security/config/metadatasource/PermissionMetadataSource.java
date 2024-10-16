package com.xweb.starter.modules.security.config.metadatasource;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.modules.security.dao.AccountDao;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.PathContainer;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PermissionMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final AccountDao accountDao;
    private Map<String, Collection<ConfigAttribute>> permissionMap;
    private final PathPatternParser parser = new PathPatternParser();

    @PostConstruct
    public void loadRolePermissions() {
       permissionMap = accountDao.loadRolePermissions();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        var request = (HttpServletRequest) object;
        var requestURI = request.getRequestURI();
        var requestMethod = request.getMethod();

        // 从权限服务中加载 URL 对应的权限
        PathPattern pattern;
        var matchedRequestURI = "";
        for (var urlKey : permissionMap.keySet()) {

            var urlKeyArr = StringUtils.split(urlKey, Constants.AUTHORIZE_PERMISSION_METADATA_SOURCE_PERMISSION_KEY_DELIMITER);
            var mapRequestMethod = urlKeyArr[0];
            pattern = parser.parse(urlKeyArr[1]);

            var matched = StringUtils.equalsIgnoreCase(mapRequestMethod, requestMethod) &&
                    pattern.matches(PathContainer.parsePath(requestURI));
            if (matched) {
                matchedRequestURI = urlKey;
                break;
            }
        }
        if (StringUtils.isBlank(matchedRequestURI)) {
            return null;
        }
        return permissionMap.get(matchedRequestURI);
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
