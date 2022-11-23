package com.crewmate.shopauth.service.login.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.crewmate.shopauth.Admin;
import com.crewmate.shopauth.ResponseMessage;
import com.crewmate.shopauth.service.login.LoginFeignClient;
import com.crewmate.shopauth.service.login.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    
    @Value("${application.api.url.system}")
    private String systemApiUrl;
    
    @Override
    public Admin getAdminInfo(Admin admin) throws JsonMappingException, JsonProcessingException {
        LoginFeignClient loginFeignClient = Feign.builder()
                                                .encoder(new JacksonEncoder()) //해당 인코더는 com.netflix.feign:feign-jackson:8.18.0 의존 필요
                                                .decoder(new JacksonDecoder()) //해당 디코더는 com.netflix.feign:feign-jackson:8.18.0 의존 필요
                                                .target(LoginFeignClient.class, systemApiUrl);
        
        ResponseMessage rm = loginFeignClient.getAdminInfo(admin.getAdminId());
        
        ObjectMapper mapper = new ObjectMapper();
        
        log.info("FEIGN : {}", mapper.readValue(mapper.writeValueAsString(rm.getData()), Admin.class));
        
        return mapper.readValue(mapper.writeValueAsString(rm.getData()), Admin.class);
    }
}
