package com.xweb.starter.modules.schedule.service;

import com.xweb.starter.modules.schedule.req.JobReq;
import com.xweb.starter.modules.schedule.resp.JobResp;

import java.util.Date;

public interface SchedulerService {

    /**
     * 查询系统现有的定时任务列表
     */
    JobResp list();

    /**
     * 手动执行任务
     */
    void run(JobReq jobReq);
    /**
     * 暂停任务
     */
    void pause(JobReq jobReq);

    /**
     * 唤起任务
     */
    void resume(JobReq jobReq);

    /**
     * 删除任务
     */
    boolean delete(JobReq jobReq);

    Date rescheduleJob(JobReq jobReq);

    Date saveJob(JobReq req);

}
