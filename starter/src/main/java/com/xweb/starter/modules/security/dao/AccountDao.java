package com.xweb.starter.modules.security.dao;

import com.xweb.starter.modules.security.domain.bo.Role;
import com.xweb.starter.modules.security.domain.entity.MastAccount;
import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface AccountDao {

    MastAccount selectForLogin(String account);

    Set<Role> selectAccountRelatedPermissions(Long accountId);

    Map<String, Collection<ConfigAttribute>> loadButtonPermissions();

}
