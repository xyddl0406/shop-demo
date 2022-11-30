package com.crewmate.shopapicommon.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiLog {
    private long logSeq;
    private String requestMethod;
    private String requestUri;
    private String successYn;
    private long execTime;
    private String requestParam;
    private String requestBody;
    private String responseResult;
    private String errMsg;
    private String errLog;
    private String clientIp;
    private String userAgent;
    private String regDt;
}
