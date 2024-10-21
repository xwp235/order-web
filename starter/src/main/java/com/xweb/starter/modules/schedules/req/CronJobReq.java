package com.xweb.starter.modules.schedules.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CronJobReq extends JobReq{

    private String cronExpression;

}
