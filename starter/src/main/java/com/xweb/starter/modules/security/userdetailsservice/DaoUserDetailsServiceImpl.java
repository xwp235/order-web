package com.xweb.starter.modules.security.userdetailsservice;

import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.modules.security.dao.AccountDao;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("daoUserDetailsService")
@RequiredArgsConstructor
public class DaoUserDetailsServiceImpl implements UserDetailsService {

    private final AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // 1. 通过用户名到数据库查询用户信息
        var dbAccount = accountDao.selectForLogin(account);
        if (Objects.isNull(dbAccount)) {
            throw new UsernameNotFoundException(account);
        }
        // 2. 如果用户存在则查询用户相关的角色和权限信息
        var roleSet = accountDao.selectAccountRelatedPermissions(dbAccount.getId());
        roleSet.forEach(item->{
            item.setBuiltIn(!item.getBuiltIn());
            item.setMrId(Constants.ROLE_PREFIX+ item.getMrId());
        });
        // 3. 封装用户信息到SecureUser类中
        var loginUser = new SecureUser();
        loginUser.setId(dbAccount.getId())
                .setNickname(dbAccount.getNickname())
                .setUsername(dbAccount.getUsername())
                .setMobile(dbAccount.getMobile())
                .setEmail(dbAccount.getEmail())
                .setPassword(dbAccount.getPassword())
                .setAccountExpired(dbAccount.getAccountExpired())
                .setAccountLocked(dbAccount.getAccountLocked())
                .setPasswordExpired(dbAccount.getPasswordExpired())
                .setMfaKey(dbAccount.getMfaKey())
                .setUsingMfa(dbAccount.getUsingMfa())
                .setEnabled(dbAccount.getEnabled())
                .setCreateTime(dbAccount.getCreateTime())
                .setRoles(roleSet);
        return loginUser;
    }

}
