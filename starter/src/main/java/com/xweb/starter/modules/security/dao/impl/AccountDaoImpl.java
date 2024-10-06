package com.xweb.starter.modules.security.dao.impl;

import com.xweb.starter.modules.security.dao.AccountDao;
import com.xweb.starter.modules.security.domain.bo.Role;
import com.xweb.starter.modules.security.domain.entity.MastAccount;
import com.xweb.starter.modules.security.helpers.SecurityHelper;
import com.xweb.starter.modules.security.mapper.cust.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class AccountDaoImpl implements AccountDao {

    private final AccountMapper accountMapper;

    @Override
    public MastAccount selectForLogin(String account) {
       return accountMapper.selectByLoginAccount(account);
    }

    @Override
    public Set<Role> selectAccountRelatedPermissions(Long accountId) {
        return accountMapper.selectAccountRelatedPermissions(accountId);
    }

    @Override
    public Map<String, Collection<ConfigAttribute>> loadButtonPermissions() {
        var permissionsMap = new HashMap<String, Collection<ConfigAttribute>>();
        var urlBtnPermissions = accountMapper.selectButtonPermissions();
        for (var urlBtnPermission : urlBtnPermissions) {
            var mapKey = SecurityHelper.generateBtnPermissionMapKey(urlBtnPermission.getRequestMethod(),urlBtnPermission.getRequestUrl());
            var mapSetItemVal = urlBtnPermission.getPermissionKey();
            Collection<ConfigAttribute> itemBtnPermissions;
            if (!permissionsMap.containsKey(mapKey)) {
                itemBtnPermissions = new HashSet<>();
                itemBtnPermissions.add(new SecurityConfig(mapSetItemVal));
                permissionsMap.put(mapKey, itemBtnPermissions);
            } else {
                itemBtnPermissions = permissionsMap.get(mapKey);
                itemBtnPermissions.add(new SecurityConfig(mapSetItemVal));
            }
        }
        return permissionsMap;
    }

}
