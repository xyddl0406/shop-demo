package com.crewmate.shopapicommon.model;

import lombok.Data;

/**
 * API 응답 메시지
 * @author CREWMATE
 *
 */
@Data
public class ResponseMessage {
    private String message;
    private Object data;
    
    private int resultStatus = 200; //Custom resultStatus. 5xx, 4xx 등등
}
