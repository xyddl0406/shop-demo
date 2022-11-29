package com.crewmate.shopauth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewmate.shopapicommon.model.Admin;
import com.crewmate.shopapicommon.model.ResponseMessage;
import com.crewmate.shopapicommon.util.CommonUtils;
import com.crewmate.shopauth.service.login.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    
    private final LoginService loginService;
    
    @RequestMapping("/auth/login")
    public ResponseEntity<ResponseMessage> login(Admin admin, HttpServletRequest request) throws Exception {
        //응답메시지 객체 생성
        ResponseMessage rm = new ResponseMessage();
        
        //아이디, 비밀번호 필수값 체크 (서비스 안태움)
        if (StringUtils.isEmpty(admin.getAdminId()) || StringUtils.isEmpty(admin.getAdminPw())) {
            rm.setMessage("아이디 혹은 비밀번호를 확인해 주세요.");
            rm.setResultStatus(500);
            
            return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
        }
        
        //비즈니스로직은 service단에서 해야하는데, 세션을 생성, 저장하려면 컨트롤러단에서 해야해서 여기서 구현
        //서비스단에서 할수있는방법이 있는지 확인필요.
        //관리자정보 조회
        Admin dbAdmin = this.loginService.getAdminInfo(admin);
        log.info("LoginController.login.admin : {}", admin);
        log.info("LoginController.login.dbAdmin : {}", dbAdmin);
        log.info("LoginController.login.sha system:1234 : {}", CommonUtils.sha512(admin.getAdminPw(), admin.getAdminId()));
        
        //존재하지않는 관리자 와 비밀번호 오류는 로직상 다르지만
        //보안을위하여 client단에는 같은 응답으로 처리
        //이후 로직 변경 가능성 있음.
        if (dbAdmin == null) {
            rm.setMessage("아이디 또는 비밀번호를 확인해 주세요.");
            rm.setResultStatus(500);
            //response.sendRedirect("http://shop.co.kr/login/form");
        } else if (!StringUtils.equals(CommonUtils.sha512(admin.getAdminPw(), admin.getAdminId()) , dbAdmin.getAdminPw())) {
            rm.setMessage("아이디 또는 비밀번호를 확인해 주세요.");
            rm.setResultStatus(500);
            //response.sendRedirect("http://shop.co.kr/login/form");
        } else {
            //세션 저장
            CommonUtils.getSession().setAttribute("BO_AUTHORIZATION", dbAdmin);
            //HttpSession session = request.getSession();
            //session.setAttribute("BO_AUTHORIZATION", dbAdmin);
            rm.setData(dbAdmin);
        }
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }

    @RequestMapping("/auth/check")
    public ResponseEntity<ResponseMessage> authCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        ResponseMessage rm = new ResponseMessage();
        rm.setMessage(HttpStatus.OK.getReasonPhrase());
        
        if (session == null || session.getAttribute("BO_AUTHORIZATION") == null) {
            rm.setData(false);
        } else {
            rm.setData(true);
        }
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
    
    @RequestMapping("/auth/logout")
    public ResponseEntity<ResponseMessage> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        ResponseMessage rm = new ResponseMessage();
        rm.setMessage(HttpStatus.OK.getReasonPhrase());
        
        if (session.getAttribute("BO_AUTHORIZATION") == null) {
            //유효하지않은 로그인 세션임.
            rm.setMessage("유효하지 않은 로그인 정보 입니다.");
            rm.setData(false);
        } else {
            //유효한 인증세션만 만료시킨다.
            log.info("logout! : {}", session.getId());
            rm.setMessage("로그아웃 되었습니다.");
            session.invalidate();
            rm.setData(true);
        }
        
        return new ResponseEntity<ResponseMessage>(rm, HttpStatus.OK);
    }
}
