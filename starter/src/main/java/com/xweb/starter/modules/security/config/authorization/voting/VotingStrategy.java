package com.xweb.starter.modules.security.config.authorization.voting;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * ConsensusBased：当投票者对授权决策有多数支持时授权通过。
 * AffirmativeBased：只要有一个投票者支持授权则通过。
 * UnanimousBased：所有投票者都支持才能授权通过。
 */
public interface VotingStrategy {

    AuthorizationDecision vote(Authentication authentication, Collection<ConfigAttribute> requiredAuthorities);

}
