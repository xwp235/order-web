package com.xweb.starter.modules.schedule.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CronJobReq extends JobReq{

    private String cronExpression;

}
