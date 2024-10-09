package com.xweb.starter.modules.security.config.authorization;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.modules.security.config.authorization.voting.CountVote;
import com.xweb.starter.modules.security.helpers.SecurityHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class VotingUtil {

    public static CountVote getCountVote(Authentication authentication, Collection<ConfigAttribute> requiredAttributes) {
        var roleCount =  authentication.getAuthorities().stream().filter(grantedAuthority ->
                grantedAuthority.getAuthority().startsWith(Constants.ROLE_PREFIX)
                        && !grantedAuthority.getAuthority().contains(Constants.AUTHORIZE_PERMISSION_KEY_DELIMITER)).count();
        var permCount = 0L;
        for (var requiredAttr : requiredAttributes) {
            var permissionKey = requiredAttr.getAttribute();
            permCount = authentication.getAuthorities().stream()
                    .filter(item-> {
                        if (!item.getAuthority().contains(Constants.AUTHORIZE_PERMISSION_KEY_DELIMITER)) {
                            return false;
                        }
                        return StringUtils.equals(permissionKey, item.getAuthority().split("\\"+Constants.AUTHORIZE_PERMISSION_KEY_DELIMITER)[1]);
                    }).count();
        }
        return new CountVote(roleCount, permCount);
    }

}
