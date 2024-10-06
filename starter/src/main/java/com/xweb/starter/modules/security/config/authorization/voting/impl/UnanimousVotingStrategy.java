package com.xweb.starter.modules.security.config.authorization.voting.impl;

import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UnanimousVotingStrategy implements VotingStrategy {

    @Override
    public AuthorizationDecision vote(Authentication authentication, Collection<ConfigAttribute> requiredAttributes) {
        for (var requiredAttr : requiredAttributes) {
            var permissionKey = requiredAttr.getAttribute();
            boolean hasAuthority = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(key-> StringUtils.equals(permissionKey,key));

            if (!hasAuthority) {
                return new AuthorizationDecision(false);
            }
        }

        return new AuthorizationDecision(true);
    }
}
