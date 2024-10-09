package com.xweb.starter.modules.security.config.authorization;

import com.xweb.starter.modules.security.config.authorization.voting.impl.AffirmativeBasedVotingStrategy;
import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.function.Supplier;

/**
 * todo 根据不同路径使用不同授权策略
 */
public class CompositeAuthorizationManager implements AuthorizationManager<HttpServletRequest> {

    private final VotingAuthorizationManager authorizationManager;
    public CompositeAuthorizationManager(PermissionMetadataSource metadataSource) {
        this.authorizationManager = new VotingAuthorizationManager(metadataSource, new AffirmativeBasedVotingStrategy());
    }

    private final AntPathRequestMatcher pathMatcher = new AntPathRequestMatcher("/**");

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest request) {
        var needAuthorizePath = pathMatcher.matches(request);
        if (needAuthorizePath) {
            return authorizationManager.check(authentication, request);
        }
        return new AuthorizationDecision(false);
    }

}
