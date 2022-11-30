package com.crewmate.shopapisystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crewmate.shopapicommon.model.ApiLog;
import com.crewmate.shopapicommon.model.ResponseMessage;
import com.crewmate.shopapisystem.service.apilog.ApiLogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ApiLogController {
    
    private final ApiLogService ApiLogService;

    @PostMapping("/api-log")
    public ResponseEntity<ResponseMessage> insertApiLog(@RequestBody ApiLog apiLog) {
        
        log.info("ApiLog : {}", apiLog);
        ResponseMessage rm = new ResponseMessage();
        rm.setData(this.ApiLogService.insertApiLog(apiLog));
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
    
}
