package com.xweb.starter.modules.security.config.authorization;

import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

/**
 * 投票授权管理器
 */
@RequiredArgsConstructor
public class VotingAuthorizationManager implements AuthorizationManager<HttpServletRequest> {

    private final PermissionMetadataSource permissionMetadataSource;
    private final VotingStrategy votingStrategy;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest request) {
        var requiredAttributes = permissionMetadataSource.getAttributes(request);
        // 如果访问的url没有授权的需求则直接跳过
        if (CollectionUtils.isEmpty(requiredAttributes)) {
            return new AuthorizationDecision(false);
        }
        return votingStrategy.vote(authentication.get(), requiredAttributes);
    }

    public AuthorizationDecision check(Supplier<Authentication> authentication, String requestURI,String method) {
        var requiredAttributes = permissionMetadataSource.getRequiredAttributes(requestURI,method,false);
        // 如果访问的url没有授权的需求则直接跳过
        if (CollectionUtils.isEmpty(requiredAttributes)) {
            return new AuthorizationDecision(false);
        }
        return votingStrategy.vote(authentication.get(), requiredAttributes);
    }

}
