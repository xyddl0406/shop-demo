package com.crewmate.shopapicommon.event;

import org.springframework.context.ApplicationEvent;

import com.crewmate.shopapicommon.model.ApiLog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggingEvent extends ApplicationEvent {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3677844209250042437L;

    public LoggingEvent(ApiLog apiLog) {
        super(apiLog);
    }
}
