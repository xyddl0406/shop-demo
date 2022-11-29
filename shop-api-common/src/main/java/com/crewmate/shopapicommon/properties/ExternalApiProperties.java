package com.crewmate.shopapicommon.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@ConfigurationProperties(prefix = "external.api.url")
@RequiredArgsConstructor
@ConstructorBinding
@Getter
public class ExternalApiProperties {
    private final String gateway;
    private final String auth;
    private final String system;
    private final String product;
}