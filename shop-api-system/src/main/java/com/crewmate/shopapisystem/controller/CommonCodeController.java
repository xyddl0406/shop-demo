package com.crewmate.shopapisystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewmate.shopapicommon.model.ResponseMessage;
import com.crewmate.shopapisystem.model.code.CommonCodeSearch;
import com.crewmate.shopapisystem.service.code.CommonCodeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class CommonCodeController {
    
    private CommonCodeService commonCodeService;

    /**
     * 공통 코드 검색
     * @param search
     * @return
     */
    @RequestMapping("/code/list") ///system-api 게이트웨이에서 받아서 삭제
    public ResponseEntity<ResponseMessage> getCommonCodeList(CommonCodeSearch search) {
        log.info("getCommonCodeList!!! : {}", search);
        
        ResponseMessage rm = new ResponseMessage();
        rm.setMessage(HttpStatus.OK.getReasonPhrase());
        rm.setData(this.commonCodeService.getCommonCodeList(search));
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
}
