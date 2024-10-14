package com.xweb.starter.modules.security.domain.entity;

import java.time.ZonedDateTime;

/**
* Created by Mybatis Generator on 2024/10/11 13:16
*/
public class HisClientLoginLog {
    /**
     */
    private String username;

    /**
     */
    private String sessionId;

    /**
     */
    private String device;

    /**
     */
    private String ip;

    /**
     */
    private Long accountId;

    /**
     */
    private ZonedDateTime loginTime;

    /**
     */
    private ZonedDateTime logoutTime;

    /**
     */
    private Boolean isOffline;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public ZonedDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(ZonedDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public ZonedDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(ZonedDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Boolean getIsOffline() {
        return isOffline;
    }

    public void setIsOffline(Boolean isOffline) {
        this.isOffline = isOffline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", username=").append(username);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", device=").append(device);
        sb.append(", ip=").append(ip);
        sb.append(", accountId=").append(accountId);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", logoutTime=").append(logoutTime);
        sb.append(", isOffline=").append(isOffline);
        sb.append("]");
        return sb.toString();
    }
}