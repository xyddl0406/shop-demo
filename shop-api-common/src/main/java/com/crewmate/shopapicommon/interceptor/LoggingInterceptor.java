package com.crewmate.shopapicommon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.crewmate.shopapicommon.model.ApiLog;
import com.crewmate.shopapicommon.service.log.SystemFeignClient;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    private final SystemFeignClient systemFeignClient;
    
    /**
     * Constructor
     * 인터셉터가 bean으로등록될때 SystemFeignClient를 bean으로 등록하면 순환참조발생
     * -> SystemFeignClient는 lazy(실제 SystemFeignClient 를 사용할때 빈을생성)로 주입받음
     * @param systemFeignClient
     */
    public LoggingInterceptor(@Lazy SystemFeignClient systemFeignClient) {
        this.systemFeignClient = systemFeignClient;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        ApiLog apiLog = new ApiLog();
        apiLog.setRequestMethod(request.getMethod());
        apiLog.setRequestUri(request.getRequestURI());
        apiLog.setSuccessYn("Y");
        apiLog.setExecTime(0);
        //apiLog.setRequestParam(request.getParameterMap());
        log.info("============start==============");
        systemFeignClient.insertApiLog(apiLog);
        
        return true;
    }
    
    

}
