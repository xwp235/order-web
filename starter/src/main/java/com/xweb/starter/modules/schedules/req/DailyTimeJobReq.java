package com.xweb.starter.modules.schedules.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.quartz.TimeOfDay;

@Data
@EqualsAndHashCode(callSuper=true)
public class DailyTimeJobReq extends JobReq{

    private TimeOfDay startTime;
    private TimeOfDay endTime;
    private Integer fixedRate;
    private Integer endingDailyAfterCount;

}
