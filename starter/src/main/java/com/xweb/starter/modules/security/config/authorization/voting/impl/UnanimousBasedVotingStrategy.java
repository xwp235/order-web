package com.xweb.starter.modules.security.config.authorization.voting.impl;

import com.xweb.starter.modules.security.config.authorization.VotingUtil;
import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class UnanimousBasedVotingStrategy implements VotingStrategy {

    @Override
    public AuthorizationDecision vote(Authentication authentication, Collection<ConfigAttribute> requiredAttributes) {
        var result = VotingUtil.getCountVote(authentication, requiredAttributes);
        return new AuthorizationDecision(result.roleCount() == result.permCount());
    }

}
