package com.xweb.starter.modules.security.config.details;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import ua_parser.Parser;

import java.io.Serializable;

@Getter
@ToString
public class LoginExtraDetails extends WebAuthenticationDetails implements Serializable {

    private final String device;

    public LoginExtraDetails(HttpServletRequest request) {
        super(request);
        var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        var uaParser = new Parser();
        var ua = uaParser.parse(userAgent);
        this.device = ua.os.family;
    }

}
