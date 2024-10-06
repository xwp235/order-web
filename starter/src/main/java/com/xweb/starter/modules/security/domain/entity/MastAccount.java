package com.xweb.starter.modules.security.domain.entity;

import java.time.ZonedDateTime;

/**
* Created by Mybatis Generator on 2024/10/05 09:56
*/
public class MastAccount {
    /**
     */
    private Long id;

    /**
     */
    private String avatar;

    /**
     */
    private String nickname;

    /**
     */
    private String username;

    /**
     */
    private String mobile;

    /**
     */
    private String email;

    /**
     */
    private String password;

    /**
     */
    private Boolean accountExpired;

    /**
     */
    private Boolean accountLocked;

    /**
     */
    private Boolean passwordExpired;

    /**
     */
    private String mfaKey;

    /**
     */
    private Boolean usingMfa;

    /**
     */
    private Boolean enabled;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Boolean getPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(Boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public String getMfaKey() {
        return mfaKey;
    }

    public void setMfaKey(String mfaKey) {
        this.mfaKey = mfaKey;
    }

    public Boolean getUsingMfa() {
        return usingMfa;
    }

    public void setUsingMfa(Boolean usingMfa) {
        this.usingMfa = usingMfa;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
        sb.append(", avatar=").append(avatar);
        sb.append(", nickname=").append(nickname);
        sb.append(", username=").append(username);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", accountExpired=").append(accountExpired);
        sb.append(", accountLocked=").append(accountLocked);
        sb.append(", passwordExpired=").append(passwordExpired);
        sb.append(", mfaKey=").append(mfaKey);
        sb.append(", usingMfa=").append(usingMfa);
        sb.append(", enabled=").append(enabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}