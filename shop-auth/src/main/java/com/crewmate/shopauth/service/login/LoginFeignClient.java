package com.crewmate.shopauth.service.login;

import com.crewmate.shopapicommon.model.ResponseMessage;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

//@FeignClient(name = "loginFeignClient", url = "${application.api.url.system}")
public interface LoginFeignClient {
    
    //@RequestMapping(method=RequestMethod.GET, value="/admin/info?adminId={adminId}")
    @RequestLine("GET /admin/info?adminId={adminId}")
    @Headers("Content-Type: application/json")
    ResponseMessage getAdminInfo(@Param(value = "adminId") String adminId);

}
