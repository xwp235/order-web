package com.xweb.starter.modules.security.config.authorization.voting.impl;

import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class ConsensusVotingStrategy implements VotingStrategy {

    @Override
    public AuthorizationDecision vote(Authentication authentication, Collection<ConfigAttribute> requiredAttributes) {
        var grantVotes = 0;
        var denyVotes = 0;

        for (var requiredAttr : requiredAttributes) {
            var permissionKey = requiredAttr.getAttribute();
            var hasAuthority = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(key-> StringUtils.equals(permissionKey,key));
            if (hasAuthority) {
                grantVotes++;
            } else {
                denyVotes++;
            }
        }
        return new AuthorizationDecision(grantVotes > denyVotes);
    }

}
