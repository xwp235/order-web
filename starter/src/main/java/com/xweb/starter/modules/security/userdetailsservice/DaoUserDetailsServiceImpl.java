package com.xweb.starter.modules.security.userdetailsservice;

import com.xweb.starter.modules.security.dao.AccountDao;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import com.xweb.starter.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service("daoUserDetailsService")
@RequiredArgsConstructor
public class DaoUserDetailsServiceImpl implements UserDetailsService {

    private final AccountDao accountDao;
    private final GrantedAuthoritiesMapper authoritiesMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // 1. 通过用户名到数据库查询用户信息
        var dbAccount = accountDao.selectForLogin(account);
        if (Objects.isNull(dbAccount)) {
            throw new UsernameNotFoundException(account);
        }
        // 2. 如果用户存在则查询用户相关的角色和权限信息
        var accountRoles = accountDao.selectAccountRelatedRoles(dbAccount.getId());
        var accountRoleAuthorities = accountRoles.stream().map(role->new SimpleGrantedAuthority(role.getMrId())).collect(Collectors.toSet());
        var hierarchyRoles = authoritiesMapper.mapAuthorities(accountRoleAuthorities);
        var relatedRoles = hierarchyRoles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        var roleSet = accountDao.selectAccountRelatedPermissions(relatedRoles);
        roleSet.forEach(role -> {
            var permissions = role.getPermissions();
            if(CollectionUtils.isNotEmpty(permissions)){
                permissions.forEach(permission->permission.setRoleId(role.getMrId()));
            }
        });

        // 3. 封装用户信息到SecureUser类中
        var loginUser = new SecureUser();
        loginUser.setAccountId(dbAccount.getId())
                .setNickname(dbAccount.getNickname())
                .setUsername(dbAccount.getUsername())
                .setMobile(dbAccount.getMobile())
                .setEmail(dbAccount.getEmail())
                .setPassword(dbAccount.getPassword())
                .setAccountExpired(dbAccount.getAccountExpired())
                .setAccountLocked(dbAccount.getAccountLocked())
                .setPasswordExpired(dbAccount.getPasswordExpired())
                .setUsingMfa(dbAccount.getUsingMfa())
                .setMfaKey(dbAccount.getMfaKey())
                .setEnabled(dbAccount.getEnabled())
                .setCreateTime(dbAccount.getCreateTime())
                .setRoles(roleSet);
        return loginUser;
    }

}
