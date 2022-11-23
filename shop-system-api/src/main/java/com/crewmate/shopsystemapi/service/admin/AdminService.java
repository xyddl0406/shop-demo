package com.crewmate.shopsystemapi.service.admin;

import com.crewmate.shopsystemapi.model.admin.Admin;

public interface AdminService {
    Admin selectAdminInfo(String adminId);
}
