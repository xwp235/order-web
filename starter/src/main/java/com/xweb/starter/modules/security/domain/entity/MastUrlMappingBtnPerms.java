package com.xweb.starter.modules.security.domain.entity;

import java.time.ZonedDateTime;

/**
* Created by Mybatis Generator on 2024/10/05 09:56
*/
public class MastUrlMappingBtnPerms {
    /**
     */
    private Long id;

    /**
     */
    private String requestUrl;

    /**
     */
    private String requestMethod;

    /**
     */
    private String buttonPermissionKey;

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

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getButtonPermissionKey() {
        return buttonPermissionKey;
    }

    public void setButtonPermissionKey(String buttonPermissionKey) {
        this.buttonPermissionKey = buttonPermissionKey;
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
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", buttonPermissionKey=").append(buttonPermissionKey);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}