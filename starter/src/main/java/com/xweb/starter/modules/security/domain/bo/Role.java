package com.xweb.starter.modules.security.domain.bo;

import com.xweb.starter.common.dbmappingtypes.MultiLanguage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(of="mrId")
public class Role {

    private Long id;

    private String mrId;

    private MultiLanguage mrName;

    private Integer mrWeight;

    private Boolean builtIn;

    private Set<Permission> permissions;

}
