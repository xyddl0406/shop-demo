package com.crewmate.shopapicommon.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@ConfigurationProperties
@ConstructorBinding
public class ApplicationProperties {
    private final Spring spring;
    
    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class Spring {
        private final Datasource datasource;
        
        @Getter
        @ToString
        @RequiredArgsConstructor
        public static class Datasource {
            private final Hikari hikari;
            
            @Getter
            @ToString
            @RequiredArgsConstructor
            public static class Hikari {
                private final String jdbcUrl;
                private final String driverClassName;
                private final String username;
                private final String password;
            }
        }
    }
}