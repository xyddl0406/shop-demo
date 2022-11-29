package com.crewmate.shopapisystem.service.admin;

import com.crewmate.shopapicommon.model.Admin;

public interface AdminService {
    Admin selectAdminInfo(String adminId);
}
