package com.crewmate.shopapiproduct.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewmate.shopapicommon.model.ResponseMessage;
import com.crewmate.shopapicommon.properties.ExternalApiProperties;
import com.crewmate.shopapicommon.util.CommonUtils;
import com.crewmate.shopapiproduct.service.comp.CompService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CompController {

    private final CompService compService;
    
    private final ExternalApiProperties externalApiConfig;
    
    @RequestMapping("/company/list")
    public ResponseEntity<ResponseMessage> getCompanyList() {
        
        CommonUtils.getTest();
        
        log.info("externalApiConfig : {}", externalApiConfig);
        
        ResponseMessage rm = new ResponseMessage();
        rm.setData(this.compService.getCompList());
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK) ;
    }
}
