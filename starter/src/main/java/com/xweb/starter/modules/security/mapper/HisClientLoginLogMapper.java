package com.xweb.starter.modules.security.mapper;

import com.xweb.starter.modules.security.domain.entity.HisClientLoginLog;
import com.xweb.starter.modules.security.domain.entity.HisClientLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HisClientLoginLogMapper {
    long countByExample(HisClientLoginLogExample example);

    int deleteByExample(HisClientLoginLogExample example);

    int deleteByPrimaryKey(@Param("username") String username, @Param("sessionId") String sessionId, @Param("device") String device);

    int insert(HisClientLoginLog row);

    int insertSelective(HisClientLoginLog row);

    List<HisClientLoginLog> selectByExample(HisClientLoginLogExample example);

    HisClientLoginLog selectByPrimaryKey(@Param("username") String username, @Param("sessionId") String sessionId, @Param("device") String device);

    int updateByExampleSelective(@Param("row") HisClientLoginLog row, @Param("example") HisClientLoginLogExample example);

    int updateByExample(@Param("row") HisClientLoginLog row, @Param("example") HisClientLoginLogExample example);

    int updateByPrimaryKeySelective(HisClientLoginLog row);

    int updateByPrimaryKey(HisClientLoginLog row);
}