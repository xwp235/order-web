package com.xweb.starter.modules.security.mapper;

import com.xweb.starter.modules.security.domain.entity.MastAccount;
import com.xweb.starter.modules.security.domain.entity.MastAccountExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MastAccountMapper {
    long countByExample(MastAccountExample example);

    int deleteByExample(MastAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MastAccount row);

    int insertSelective(MastAccount row);

    List<MastAccount> selectByExample(MastAccountExample example);

    MastAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MastAccount row, @Param("example") MastAccountExample example);

    int updateByExample(@Param("row") MastAccount row, @Param("example") MastAccountExample example);

    int updateByPrimaryKeySelective(MastAccount row);

    int updateByPrimaryKey(MastAccount row);
}
