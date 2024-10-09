package com.xweb.starter.modules.security.domain.bo;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.common.dbmappingtypes.MultiLanguage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@Data
@EqualsAndHashCode(of={"permissionKey","roleId"})
public class Permission implements GrantedAuthority {

    /**
     * 每项菜单的唯一标识
     */
    private String permissionKey;
    private String roleId;

    private MultiLanguage name;

    @Override
    public String getAuthority() {
        return roleId+ Constants.AUTHORIZE_PERMISSION_KEY_DELIMITER+permissionKey;
    }

}
