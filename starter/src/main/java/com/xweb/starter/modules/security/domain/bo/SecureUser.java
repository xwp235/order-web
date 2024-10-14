package com.xweb.starter.modules.security.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of={"username","mobile","email"})
public class SecureUser implements UserDetails, CredentialsContainer {

    @Getter
    @Setter
    private Long accountId;

    @Getter
    @Setter
    private String avatar;

    @Getter
    @Setter
    private String nickname;

    @Setter
    private String username;

    @Getter
    @Setter
    private String mobile;

    @Getter
    @Setter
    private String email;

    @Setter
    @JsonIgnore
    private String password;

    @Setter
    private Boolean accountExpired;

    @Setter
    private Boolean accountLocked;

    @Setter
    private Boolean passwordExpired;

    @Getter
    @Setter
    private String mfaKey;

    @Getter
    @Setter
    private Boolean usingMfa;

    @Setter
    private Boolean enabled;

    @Getter
    @Setter
    private ZonedDateTime createTime;

    @Getter
    @Setter
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .flatMap(role -> Stream.concat(
                        Stream.of(new SimpleGrantedAuthority(role.getMrId())),
                        role.getPermissions().stream())
                // 使用toSet会使投票策略无效，故使用允许权限flag重复
                ).toList();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.passwordExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

}
