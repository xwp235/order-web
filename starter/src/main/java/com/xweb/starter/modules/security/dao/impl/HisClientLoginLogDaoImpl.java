package com.xweb.starter.modules.security.dao.impl;

import com.xweb.starter.modules.security.config.details.LoginExtraDetails;
import com.xweb.starter.modules.security.dao.HisClientLoginLogDao;
import com.xweb.starter.modules.security.domain.bo.SecureUser;
import com.xweb.starter.modules.security.domain.entity.HisClientLoginLog;
import com.xweb.starter.modules.security.domain.entity.HisClientLoginLogExample;
import com.xweb.starter.modules.security.mapper.HisClientLoginLogMapper;
import com.xweb.starter.modules.security.mapper.cust.CustHisClientLoginLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class HisClientLoginLogDaoImpl implements HisClientLoginLogDao {

    private final CustHisClientLoginLogMapper custHisClientLoginLogMapper;
    private final HisClientLoginLogMapper hisClientLoginLogMapper;

    @Override
    public void saveClientLoginLog(Authentication auth) {
        var userLoginLog = new HisClientLoginLog();
        var authDetails = ((LoginExtraDetails)auth.getDetails());
        userLoginLog.setSessionId(authDetails.getSessionId());
        userLoginLog.setIsOffline(false);
        userLoginLog.setDevice(authDetails.getDevice());
        var ipAddress = authDetails.getRemoteAddress();
        var clientIp = Objects.nonNull(ipAddress) && ipAddress.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1": ipAddress;
        userLoginLog.setIp(clientIp);
        var secureUser = (SecureUser)auth.getPrincipal();
        userLoginLog.setAccountId(secureUser.getAccountId());
        userLoginLog.setUsername(secureUser.getUsername());
        var dbLoginLog = selectById(auth);
        if (Objects.nonNull(dbLoginLog)) {
            custHisClientLoginLogMapper.updateUserLoginInfo(userLoginLog);
            return;
        }
        custHisClientLoginLogMapper.saveUserLoginInfo(userLoginLog);
    }

    @Override
    public HisClientLoginLog selectById(Authentication auth) {
        var secureUser = (SecureUser)auth.getPrincipal();
        var authDetails = ((LoginExtraDetails)auth.getDetails());
        return hisClientLoginLogMapper.selectByPrimaryKey(secureUser.getUsername(),
                authDetails.getSessionId(),authDetails.getDevice());
    }

    @Override
    public String updateUserOffline(Authentication auth) {
        var clientLoginLog = selectById(auth);
        if (Objects.nonNull(clientLoginLog)){
            updateUserOffline(clientLoginLog);
        }
        return clientLoginLog.getSessionId();
    }

    @Override
    public void updateUserOffline(HisClientLoginLog clientLoginLog) {
        clientLoginLog.setIsOffline(true);
        custHisClientLoginLogMapper.saveUserLogoutInfo(clientLoginLog);
    }

    @Override
    public List<HisClientLoginLog> selectValidLoginUserLogs() {
        var clientLoginLogExample = new HisClientLoginLogExample();
        var criteria = clientLoginLogExample.createCriteria();
        criteria.andLogoutTimeIsNull();
        return hisClientLoginLogMapper.selectByExample(clientLoginLogExample);
    }

}
