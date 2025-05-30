package com.xweb.starter.modules.security.config.authorization.voting.impl;

import com.xweb.starter.modules.security.config.authorization.VotingUtil;
import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
  含义：必须所有的 AccessDecisionVoter 都同意（投赞成票）才能授权。
  工作机制：
    如果任何一个 voter 投了反对票（ACCESS_DENIED），则拒绝访问。
    如果没有反对票，且至少一个 voter 投了赞成票（ACCESS_GRANTED），则授权成功。
 */   
public class UnanimousBasedVotingStrategy implements VotingStrategy {

    @Override
    public AuthorizationDecision vote(Authentication authentication, Collection<ConfigAttribute> requiredAttributes) {
        var result = VotingUtil.getCountVote(authentication, requiredAttributes);
        return new AuthorizationDecision(result.roleCount() == result.permCount());
    }

}
