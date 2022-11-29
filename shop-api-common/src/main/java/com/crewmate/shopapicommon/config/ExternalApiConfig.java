package com.crewmate.shopapicommon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
//@Component
@ConfigurationProperties(prefix = "external.api.url")
@RequiredArgsConstructor
@ConstructorBinding
@Getter
public class ExternalApiConfig {
    private final String gateway;
    private final String auth;
    private final String system;
    private final String product;
}
