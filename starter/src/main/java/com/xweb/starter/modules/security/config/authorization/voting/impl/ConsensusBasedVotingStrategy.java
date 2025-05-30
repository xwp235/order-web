package com.xweb.starter.modules.security.config.authorization.voting.impl;

import com.xweb.starter.modules.security.config.authorization.VotingUtil;
import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
  共识策略
  含义：多数同意即可授权。
  工作机制：
    统计所有投票者的票数。
    如果“赞成票” > “反对票”，则授权。
    如果“赞成票” ≤ “反对票”，则拒绝。
    ABSTAIN（弃权票）不计入票数。
*/
public class ConsensusBasedVotingStrategy implements VotingStrategy {

    @Override
    public AuthorizationDecision vote(Authentication authentication, Collection<ConfigAttribute> requiredAttributes) {
        var result = VotingUtil.getCountVote(authentication, requiredAttributes);
        return new AuthorizationDecision(result.permCount() > (result.roleCount() / 2));
    }

}
