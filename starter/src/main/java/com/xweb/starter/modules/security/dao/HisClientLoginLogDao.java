package com.xweb.starter.modules.security.dao;

import com.xweb.starter.modules.security.domain.entity.HisClientLoginLog;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface HisClientLoginLogDao {

    void saveClientLoginLog(Authentication auth);

    HisClientLoginLog selectById(Authentication auth);

    String updateUserOffline(Authentication auth);
    void updateUserOffline(HisClientLoginLog clientLoginLog);


    List<HisClientLoginLog> selectValidLoginUserLogs();

}
