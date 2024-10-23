package com.xweb.starter.modules.security.config.authorization;

import com.xweb.starter.modules.security.config.authorization.voting.impl.AffirmativeBasedVotingStrategy;
import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import com.xweb.starter.modules.security.dao.MenuDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * todo 根据不同路径使用不同授权策略
 */
@Component
public class CompositeAuthorizationManager implements AuthorizationManager<HttpServletRequest> {

    private final VotingAuthorizationManager authorizationManager;
    private final OrRequestMatcher orRequestMatcher;

    public CompositeAuthorizationManager(PermissionMetadataSource metadataSource, MenuDao menuDao) {
        this.authorizationManager = new VotingAuthorizationManager(metadataSource, new AffirmativeBasedVotingStrategy());
        var paths = menuDao.needAuthenticationUrlPath();
        var antMatchers = new AntPathRequestMatcher[paths.length];
        for (var i = 0; i < paths.length; i++) {
            antMatchers[i] = new AntPathRequestMatcher(paths[i]);
        }
        orRequestMatcher = new OrRequestMatcher(antMatchers);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest request) {
        var needAuthorizePath = orRequestMatcher.matches(request);
        if (needAuthorizePath) {
            return authorizationManager.check(authentication, request);
        }
        return new AuthorizationDecision(true);
    }

    public AuthorizationDecision check(Supplier<Authentication> authentication, String requestURI,String method) {
        return authorizationManager.check(authentication, requestURI, method);
    }

}
