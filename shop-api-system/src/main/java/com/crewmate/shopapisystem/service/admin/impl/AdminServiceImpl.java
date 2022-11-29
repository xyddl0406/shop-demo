package com.crewmate.shopapisystem.service.admin.impl;

import org.springframework.stereotype.Service;

import com.crewmate.shopapisystem.model.admin.Admin;
import com.crewmate.shopapisystem.service.admin.AdminMapper;
import com.crewmate.shopapisystem.service.admin.AdminService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    
    private AdminMapper adminMapper;

    @Override
    public Admin selectAdminInfo(String adminId) {
        Admin admin = this.adminMapper.selectAdminInfo(adminId);
        log.info("admin : {}", admin);
        return admin;
    }
}
