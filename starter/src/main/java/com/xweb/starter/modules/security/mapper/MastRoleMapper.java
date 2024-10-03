package com.xweb.starter.modules.security.mapper;

import com.xweb.starter.modules.security.domain.entity.MastRole;
import com.xweb.starter.modules.security.domain.entity.MastRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MastRoleMapper {
    long countByExample(MastRoleExample example);

    int deleteByExample(MastRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MastRole row);

    int insertSelective(MastRole row);

    List<MastRole> selectByExample(MastRoleExample example);

    MastRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MastRole row, @Param("example") MastRoleExample example);

    int updateByExample(@Param("row") MastRole row, @Param("example") MastRoleExample example);

    int updateByPrimaryKeySelective(MastRole row);

    int updateByPrimaryKey(MastRole row);
}
