package com.crewmate.shopapisystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewmate.shopapicommon.model.ResponseMessage;
import com.crewmate.shopapisystem.model.code.Code;
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
    
    /**
     * 공통 코드 상세검색
     * @param search
     * @return
     */
    @RequestMapping("/code/form") ///system-api 게이트웨이에서 받아서 삭제
    public ResponseEntity<ResponseMessage> getCommonCodeForm(String codeGrp) {
        log.info("getCommonCodeForm!!! codeGrp : {}", codeGrp);
        
        ResponseMessage rm = new ResponseMessage();
        rm.setMessage(HttpStatus.OK.getReasonPhrase());
        rm.setData(this.commonCodeService.getCommonCodeForm(codeGrp));
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
    
    /**
     * 공통 코드 상세검색
     * @param search
     * @return
     */
    @RequestMapping("/code/grp") ///system-api 게이트웨이에서 받아서 삭제
    public ResponseEntity<ResponseMessage> getCommonCodeGrpList() {
        log.info("getCommonCodeGrpList!!!");
        
        ResponseMessage rm = new ResponseMessage();
        rm.setMessage(HttpStatus.OK.getReasonPhrase());
        rm.setData(this.commonCodeService.selectCommonCodeGrpList());
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
    
    @PostMapping("/code")
    public ResponseEntity<ResponseMessage> insertCode(@RequestBody @Valid Code code) {
        log.info("insertCode!!! CODE : {}", code);
        
        // 이작업을 인터셉터에서 할 순 없을까? -> BaseModelRequestBodyAdvice 구현완료
        //code.setRegSeq(CommonUtils.getCurrentAdmin().getAdminSeq());
        //code.setModSeq(CommonUtils.getCurrentAdmin().getAdminSeq());
        
        ResponseMessage rm = new ResponseMessage();
        rm.setMessage(HttpStatus.OK.getReasonPhrase());
        rm.setData(this.commonCodeService.insertCode(code));
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
}
