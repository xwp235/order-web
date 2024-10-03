package com.xweb.starter.modules.security.domain.entity;

import java.time.ZonedDateTime;

/**
* Created by Mybatis Generator on 2024/10/03 19:22
*/
public class MastRole {
    /**
     */
    private Long id;

    /**
     * 角色ID,角色的唯一标识
     */
    private String mrId;

    /**
     * 角色名称，支持多语言:{"ja-JP":"スーパー管理者","en-US":"Super Admin","zh-CN":"超级管理员"}
     */
    private Object mrName;

    /**
     * 是否可以删除角色 true=可以 false=不可以
     */
    private Boolean mrEnableDelete;

    /**
     * 是否可以修改角色信息 true=可以 false=不可以
     */
    private Boolean mrEnableEdit;

    /**
     */
    private String mrRemark;

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

    public String getMrId() {
        return mrId;
    }

    public void setMrId(String mrId) {
        this.mrId = mrId;
    }

    public Object getMrName() {
        return mrName;
    }

    public void setMrName(Object mrName) {
        this.mrName = mrName;
    }

    public Boolean getMrEnableDelete() {
        return mrEnableDelete;
    }

    public void setMrEnableDelete(Boolean mrEnableDelete) {
        this.mrEnableDelete = mrEnableDelete;
    }

    public Boolean getMrEnableEdit() {
        return mrEnableEdit;
    }

    public void setMrEnableEdit(Boolean mrEnableEdit) {
        this.mrEnableEdit = mrEnableEdit;
    }

    public String getMrRemark() {
        return mrRemark;
    }

    public void setMrRemark(String mrRemark) {
        this.mrRemark = mrRemark;
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
        sb.append(", mrId=").append(mrId);
        sb.append(", mrName=").append(mrName);
        sb.append(", mrEnableDelete=").append(mrEnableDelete);
        sb.append(", mrEnableEdit=").append(mrEnableEdit);
        sb.append(", mrRemark=").append(mrRemark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
