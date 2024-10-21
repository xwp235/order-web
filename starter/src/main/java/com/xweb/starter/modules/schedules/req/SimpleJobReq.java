package com.xweb.starter.modules.schedules.req;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SimpleJobReq extends JobReq{

    private Integer fixedRate;
    private Integer repeatCount;

}
