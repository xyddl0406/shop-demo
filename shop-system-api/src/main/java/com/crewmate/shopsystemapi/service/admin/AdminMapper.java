package com.crewmate.shopsystemapi.service.admin;

import org.apache.ibatis.annotations.Mapper;

import com.crewmate.shopsystemapi.model.admin.Admin;

@Mapper
public interface AdminMapper {
    Admin selectAdminInfo(String adminId);

}
