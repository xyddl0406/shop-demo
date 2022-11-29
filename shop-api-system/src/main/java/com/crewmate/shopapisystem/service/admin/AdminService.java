package com.crewmate.shopapisystem.service.admin;

import com.crewmate.shopapisystem.model.admin.Admin;

public interface AdminService {
    Admin selectAdminInfo(String adminId);
}
