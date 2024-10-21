package com.xweb.starter.modules.schedules.jobs;

import com.xweb.starter.modules.security.HttpSessionUtil;
import com.xweb.starter.modules.security.SecureUserHolder;
import com.xweb.starter.modules.security.dao.HisClientLoginLogDao;
import com.xweb.starter.modules.security.domain.entity.HisClientLoginLog;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.HashSet;
import java.util.Objects;

@RequiredArgsConstructor
public class CheckLoginUserSessionJob implements Job {

    private final HisClientLoginLogDao hisClientLoginLogDao;

    @Override
    public void execute(JobExecutionContext context) {
        var clientLoginLogList = hisClientLoginLogDao.selectValidLoginUserLogs();

        var invalidSessionList = new HashSet<HisClientLoginLog>();
        for (var loginLog : clientLoginLogList) {
            var sessionId = loginLog.getSessionId();

            var httpSession = HttpSessionUtil.get(sessionId);
            if (Objects.isNull(httpSession)){
                invalidSessionList.add(loginLog);
            } else {
                var isSessionValid = HttpSessionUtil.isSessionValid(sessionId);
                if (!isSessionValid) {
                    invalidSessionList.add(loginLog);
                }
            }
        }

        for (var session : invalidSessionList) {
            HttpSessionUtil.invalidateSession(session.getSessionId());
            SecureUserHolder.removeBySessionId(session.getSessionId());
            hisClientLoginLogDao.updateUserOffline(session);
        }

//        sessionIds = SecureUserHolder.getAllSessionIds();
//        httpSessionIds = HttpSessionUtil.getAllSessionIds();

//        System.out.println("过期的会话id列表:"+invalidSessionList.stream().map(HisClientLoginLog::getSessionId).collect(Collectors.toSet()));
//        System.out.println("user holder sessionIds:"+sessionIds);
//        System.out.println("http sessionIds:"+httpSessionIds);
    }

}
