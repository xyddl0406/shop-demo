package com.crewmate.shopapicommon.aop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.crewmate.shopapicommon.event.LoggingEvent;
import com.crewmate.shopapicommon.model.ApiLog;
import com.crewmate.shopapicommon.util.CommonUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final ApplicationEventPublisher applicationEventPublisher;
    
    @Around(value = "execution(* com.crewmate..controller..*(..)) && !@annotation(com.crewmate.shopapicommon.annotation.NoLogging)")
    public Object logging(ProceedingJoinPoint pjp) {
        log.info("======================================LoggingAspect START======================================");
        //return 할 Object객체 미리 생성
        Object result = null;
        
        //소요시간 측정할 스탑워치 생성
        StopWatch sw = new StopWatch();
        
        //((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletRequest request = CommonUtils.getRequest();
        
        //DB log 객체 생성 후 기본적인 데이터 세팅
        ApiLog apiLog = new ApiLog();
        apiLog.setRequestMethod(request.getMethod());
        apiLog.setRequestUri(request.getRequestURI());
        apiLog.setSuccessYn("Y");
        apiLog.setClientIp(request.getRemoteAddr());
        //apiLog.setUserAgent("?");
        
        
        JSONObject json = new JSONObject();
        
        for (Map.Entry<String, String[]>entry : request.getParameterMap().entrySet()) {
            log.info("{}, {}", entry.getKey(), entry.getValue().toString());
            json.put(entry.getKey(), Arrays.toString(entry.getValue())); //why warning?..
        }
        
        if (!json.isEmpty()) {
            apiLog.setRequestParam(json.toJSONString());
        }
        
        try {
            //시작시간 측정  -> 프로세스 실행 -> 종료시간 측정
            sw.start();
            result = pjp.proceed();
            sw.stop();
            
            apiLog.setExecTime(sw.getTime());
        } catch (Throwable e) {
            //에러 발생시 성공여부를 'N'으로 덮어씌우기 & 에러내용 담기
            apiLog.setSuccessYn("N");
            apiLog.setErrLog(e.getMessage());
            apiLog.setErrMsg(e.getCause().toString());
        } finally {
            
            //Request Body 읽기.
            try {
                
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
                StringBuffer sb = new StringBuffer();
                
                String line = br.readLine();
                
                while(line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                
                if (StringUtils.isNotEmpty(sb.toString())) {
                    apiLog.setRequestBody(sb.toString());
                }
            } catch (IOException e) {
                //RequestBody 읽을때 에러발생하면 에러로그 남기기
                log.info("RequestBody get IOException : {}", e.getMessage());
                apiLog.setRequestBody("RequestBody get IOException : " + e.getMessage());
            }
            
            //TODO responseBody set
            
            log.info("APILOG : {}", apiLog);
            
            //이벤트 발생
            applicationEventPublisher.publishEvent(new LoggingEvent(apiLog));
        }
        
        log.info("======================================LoggingAspect END======================================");
        return result;
    }

}
