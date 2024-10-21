package com.xweb.starter.modules.schedules.resp;

import lombok.Data;
import org.quartz.DateBuilder;
import org.quartz.TimeOfDay;

import java.util.Date;
import java.util.List;

@Data
public class JobResp {

    private List<CronJobResp> cronJobList;
    private List<DailyTimeJobResp> dailyTimeJobList;
    private List<SimpleJobResp> simpleJobList;

    @Data
    public static class CronJobResp {
        private String group;
        private String name;
        private String description;
        private String state;
        private String cronExpression;
        private Date nextFireTime;
        private Date preFireTime;
        private Date finalFireTime;
    }

    @Data
    public static class DailyTimeJobResp {
        private String group;
        private String name;
        private String description;
        private Date nextFireTime;
        private Date preFireTime;
        private String state;
        private TimeOfDay startTimeOfDay;
        private TimeOfDay endTimeOfDay;
        private Integer repeatCount;
        private Integer repeatInterval;
        private Date finalFireTime;
        private Integer timesTriggered;
        private DateBuilder.IntervalUnit intervalUnit;
    }

    @Data
    public static class SimpleJobResp {
        private String group;
        private String name;
        private String description;
        private Integer timesTriggered;
        private Date nextFireTime;
        private Date preFireTime;
        private String state;
        private Integer repeatCount;
        private Long repeatInterval;
    }

}
