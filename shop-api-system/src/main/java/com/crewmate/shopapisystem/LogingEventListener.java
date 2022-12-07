package com.crewmate.shopapisystem;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.crewmate.shopapicommon.event.LoggingEvent;
import com.crewmate.shopapicommon.model.ApiLog;
import com.crewmate.shopapisystem.service.apilog.ApiLogService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogingEventListener implements ApplicationListener<LoggingEvent>{
    
    private final ApiLogService apiLogService;

    @Override
    public void onApplicationEvent(LoggingEvent event) {
        ApiLog apiLog = (ApiLog) event.getSource();
        
        this.apiLogService.insertApiLog(apiLog);
    }
}
