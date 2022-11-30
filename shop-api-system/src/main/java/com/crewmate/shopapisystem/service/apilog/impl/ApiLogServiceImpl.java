package com.crewmate.shopapisystem.service.apilog.impl;

import org.springframework.stereotype.Service;

import com.crewmate.shopapicommon.model.ApiLog;
import com.crewmate.shopapisystem.service.apilog.ApiLogMapper;
import com.crewmate.shopapisystem.service.apilog.ApiLogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiLogServiceImpl implements ApiLogService {
    
    private final ApiLogMapper apiLogMapper;

    @Override
    public boolean insertApiLog(ApiLog apiLog) {
        return this.apiLogMapper.insertApiLog(apiLog);
    }
}
