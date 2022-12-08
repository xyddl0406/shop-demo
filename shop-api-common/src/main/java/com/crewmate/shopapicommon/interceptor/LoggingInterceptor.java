package com.crewmate.shopapicommon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class LoggingInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /*
        ApiLog apiLog = new ApiLog();
        apiLog.setRequestMethod(request.getMethod());
        apiLog.setRequestUri(request.getRequestURI());
        apiLog.setSuccessYn("Y");
        apiLog.setExecTime(0);
        //apiLog.setRequestParam(request.getParameterMap());
        log.info("============start==============");
        systemFeignClient.insertApiLog(apiLog);
        */
        return true;
    }
}
