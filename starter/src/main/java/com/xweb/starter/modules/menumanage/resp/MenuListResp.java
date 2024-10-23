package com.xweb.starter.modules.menumanage.resp;

import lombok.Data;

@Data
public class MenuListResp {

    /**
     */
    private Long id;

    /**
     * 菜单ID
     */
    private Integer mmId;

    /**
     * 父菜单ID，顶级菜单值为NULL
     */
    private Integer mmParentId;

    /**
     * 菜单类型，菜单=1 按钮=2 页面=3 链接=4
     */
    private Short mmType;

    /**
     * 菜单类型为菜单或页面时才需要设置
     */
    private String mmPath;

    /**
     * 请求方式
     */
    private String mmMethod;

    /**
     * 菜单状态  停用=0 启用=1,菜单类型为菜单或页面时才需要设置
     */
    private Short mmState;

    /**
     * 菜单图标，仅在菜单类型为按钮时才需要设置
     */
    private String mmIcon;

    /**
     * 菜单名称，支持多语言:{"ja-JP":"メニュー１","en-US":"Menu1","zh-CN":"菜单1"}
     */
    private String mmName;

    /**
     * 每项菜单的唯一标识
     */
    private String mmCode;

    /**
     * 菜单层级链
     */
    private String mmLevelChain;

    /**
     * 菜单所在层级
     */
    private Integer mmLevel;

    /**
     * 菜单所在层级的排序
     */
    private Integer mmSort;

    /**
     * 菜单备注
     */
    private String mmRemark;

    /**
     */
    private Boolean mmEnableEdit;

    /**
     */
    private Boolean mmEnableDelete;

    /**
     */
    private Boolean mmRequireAuth;

}
