package com.crewmate.shopauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.crewmate.shopapicommon.config.ApplicationProperties;
import com.crewmate.shopapicommon.config.ExternalApiConfig;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class, ExternalApiConfig.class})
public class ShopAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopAuthApplication.class, args);
	}

}
