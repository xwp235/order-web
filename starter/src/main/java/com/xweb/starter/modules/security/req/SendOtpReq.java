package com.xweb.starter.modules.security.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendOtpReq {

    private String mfaId;
    // SMS , EMAIL
    private String mfaType;
}
