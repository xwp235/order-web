package com.xweb.starter.modules.security.dao.impl;

import com.xweb.starter.modules.security.dao.RoleDao;
import com.xweb.starter.modules.security.domain.entity.MastRole;
import com.xweb.starter.modules.security.domain.entity.MastRoleExample;
import com.xweb.starter.modules.security.mapper.MastRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {

    private final MastRoleMapper roleMapper;

    @Override
    public List<MastRole> selectRolesOrderByWeightDesc() {
        var example = new MastRoleExample();
        example.setOrderByClause("mr_weight desc");
        return roleMapper.selectByExample(example);
    }

}
