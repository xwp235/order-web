package com.xweb.starter.modules.schedule.req;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@Data
@JsonSubTypes({
        @JsonSubTypes.Type(DailyTimeJobReq.class),
        @JsonSubTypes.Type(SimpleJobReq.class),
        @JsonSubTypes.Type(CronJobReq.class)
})
public class JobReq {

    private String group;

    private String name;

    private String description;

}
