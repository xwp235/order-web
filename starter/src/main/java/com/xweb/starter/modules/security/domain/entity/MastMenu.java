package com.xweb.starter.modules.security.domain.entity;

import java.time.ZonedDateTime;

/**
* Created by Mybatis Generator on 2024/10/03 19:22
*/
public class MastMenu {
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
     * 菜单类型，菜单=1 按钮=2 页面=3
     */
    private Short mmType;

    /**
     * 菜单类型为菜单或页面时才需要设置
     */
    private String mmPath;

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
    private Object mmName;

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
    private ZonedDateTime createTime;

    /**
     */
    private ZonedDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMmId() {
        return mmId;
    }

    public void setMmId(Integer mmId) {
        this.mmId = mmId;
    }

    public Integer getMmParentId() {
        return mmParentId;
    }

    public void setMmParentId(Integer mmParentId) {
        this.mmParentId = mmParentId;
    }

    public Short getMmType() {
        return mmType;
    }

    public void setMmType(Short mmType) {
        this.mmType = mmType;
    }

    public String getMmPath() {
        return mmPath;
    }

    public void setMmPath(String mmPath) {
        this.mmPath = mmPath;
    }

    public Short getMmState() {
        return mmState;
    }

    public void setMmState(Short mmState) {
        this.mmState = mmState;
    }

    public String getMmIcon() {
        return mmIcon;
    }

    public void setMmIcon(String mmIcon) {
        this.mmIcon = mmIcon;
    }

    public Object getMmName() {
        return mmName;
    }

    public void setMmName(Object mmName) {
        this.mmName = mmName;
    }

    public String getMmCode() {
        return mmCode;
    }

    public void setMmCode(String mmCode) {
        this.mmCode = mmCode;
    }

    public String getMmLevelChain() {
        return mmLevelChain;
    }

    public void setMmLevelChain(String mmLevelChain) {
        this.mmLevelChain = mmLevelChain;
    }

    public Integer getMmLevel() {
        return mmLevel;
    }

    public void setMmLevel(Integer mmLevel) {
        this.mmLevel = mmLevel;
    }

    public Integer getMmSort() {
        return mmSort;
    }

    public void setMmSort(Integer mmSort) {
        this.mmSort = mmSort;
    }

    public String getMmRemark() {
        return mmRemark;
    }

    public void setMmRemark(String mmRemark) {
        this.mmRemark = mmRemark;
    }

    public Boolean getMmEnableEdit() {
        return mmEnableEdit;
    }

    public void setMmEnableEdit(Boolean mmEnableEdit) {
        this.mmEnableEdit = mmEnableEdit;
    }

    public Boolean getMmEnableDelete() {
        return mmEnableDelete;
    }

    public void setMmEnableDelete(Boolean mmEnableDelete) {
        this.mmEnableDelete = mmEnableDelete;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mmId=").append(mmId);
        sb.append(", mmParentId=").append(mmParentId);
        sb.append(", mmType=").append(mmType);
        sb.append(", mmPath=").append(mmPath);
        sb.append(", mmState=").append(mmState);
        sb.append(", mmIcon=").append(mmIcon);
        sb.append(", mmName=").append(mmName);
        sb.append(", mmCode=").append(mmCode);
        sb.append(", mmLevelChain=").append(mmLevelChain);
        sb.append(", mmLevel=").append(mmLevel);
        sb.append(", mmSort=").append(mmSort);
        sb.append(", mmRemark=").append(mmRemark);
        sb.append(", mmEnableEdit=").append(mmEnableEdit);
        sb.append(", mmEnableDelete=").append(mmEnableDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
