package com.xweb.starter.modules.security.dao;

import com.xweb.starter.modules.security.domain.entity.MastRole;

import java.util.List;

public interface RoleDao {

    List<MastRole> selectRolesOrderByWeightDesc();

}
