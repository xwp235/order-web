package com.xweb.starter.modules.security.config.authorization.voting.impl;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AffirmativeBasedVotingStrategy implements VotingStrategy {

    @Override
    public AuthorizationDecision vote(Authentication authentication, Collection<ConfigAttribute> requiredAttributes) {
        for (var requiredAttr : requiredAttributes) {
            var permissionKey = requiredAttr.getAttribute();
            var hasAuthority =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .anyMatch(authority -> {
                                if (!authority.contains(Constants.AUTHORIZE_PERMISSION_KEY_DELIMITER)) {
                                    return false;
                                }
                                return StringUtils.equals(permissionKey, authority.split("\\"+Constants.AUTHORIZE_PERMISSION_KEY_DELIMITER)[1]);
                            });
            if (hasAuthority) {
                return new AuthorizationDecision(true);
            }
        }

        return new AuthorizationDecision(false);
    }

}
