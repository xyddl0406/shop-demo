package com.crewmate.shopapisystem.service.admin;

import org.apache.ibatis.annotations.Mapper;

import com.crewmate.shopapicommon.model.Admin;

@Mapper
public interface AdminMapper {
    Admin selectAdminInfo(String adminId);

}
