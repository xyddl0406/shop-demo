package com.crewmate.shopapisystem.service.apilog;

import org.apache.ibatis.annotations.Mapper;

import com.crewmate.shopapicommon.model.ApiLog;

@Mapper
public interface ApiLogMapper {
    boolean insertApiLog(ApiLog apiLog);
}
