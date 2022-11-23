package com.crewmate.shopsystemapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewmate.shopcommonapi.model.ResponseMessage;
import com.crewmate.shopsystemapi.service.admin.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {
    
    private final AdminService adminService;
    
    @RequestMapping("/admin/info")
    public ResponseEntity<ResponseMessage> getAdminInfo(String adminId) {
        log.info("AdminController.getAdminInfo : {}", adminId);
        
        ResponseMessage rm = new ResponseMessage();
        rm.setData(this.adminService.selectAdminInfo(adminId));
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
}
