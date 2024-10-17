package com.xweb.starter.modules.security.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xweb.starter.common.dbmappingtypes.MultiLanguage;
import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    private Integer id;
    private Integer parentId;
    private String path;
    private String icon;
    private MultiLanguage menuName;
    private Short menuType;
    private Integer sort;
    private Integer level;
    private String levelChain;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuVO> children;

}
