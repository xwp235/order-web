package com.xweb.starter.modules.security.mapper;

import com.xweb.starter.modules.security.domain.entity.MastMenu;
import com.xweb.starter.modules.security.domain.entity.MastMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MastMenuMapper {
    long countByExample(MastMenuExample example);

    int deleteByExample(MastMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MastMenu row);

    int insertSelective(MastMenu row);

    List<MastMenu> selectByExample(MastMenuExample example);

    MastMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") MastMenu row, @Param("example") MastMenuExample example);

    int updateByExample(@Param("row") MastMenu row, @Param("example") MastMenuExample example);

    int updateByPrimaryKeySelective(MastMenu row);

    int updateByPrimaryKey(MastMenu row);
}
