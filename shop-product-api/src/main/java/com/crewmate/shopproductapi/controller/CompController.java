package com.crewmate.shopproductapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewmate.shopcommonapi.model.ResponseMessage;
import com.crewmate.shopcommonapi.util.CommonUtils;
import com.crewmate.shopproductapi.service.comp.CompService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompController {

    private final CompService compService;
    
    @RequestMapping("/company/list")
    public ResponseEntity<ResponseMessage> getCompanyList() {
        
        CommonUtils.getTest();
        
        ResponseMessage rm = new ResponseMessage();
        rm.setData(this.compService.getCompList());
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK) ;
    }
}
