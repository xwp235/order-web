package com.xweb.starter.modules.security.domain.bo;

import com.xweb.starter.common.dbmappingtypes.MultiLanguage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@Data
@EqualsAndHashCode(of="permissionKey")
public class Permission implements GrantedAuthority {

    /**
     * 每项菜单的唯一标识
     */
    private String permissionKey;

    private MultiLanguage name;

    @Override
    public String getAuthority() {
        return permissionKey;
    }

}
