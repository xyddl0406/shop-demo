package com.crewmate.shopapicommon.service.log;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crewmate.shopapicommon.model.ApiLog;
import com.crewmate.shopapicommon.model.ResponseMessage;

@FeignClient(value = "systemFeignClient", url = "${external.api.url.system}")
public interface SystemFeignClient {
    @RequestMapping(method = RequestMethod.POST, value="/api-log", consumes = "application/json")
    ResponseEntity<ResponseMessage> insertApiLog(ApiLog apiLog);
}
