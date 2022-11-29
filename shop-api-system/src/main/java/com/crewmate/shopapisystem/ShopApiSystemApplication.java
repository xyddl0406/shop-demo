package com.crewmate.shopapisystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.crewmate.shopapicommon.properties.ApplicationProperties;
import com.crewmate.shopapicommon.properties.ExternalApiProperties;

@EnableConfigurationProperties({ApplicationProperties.class, ExternalApiProperties.class})
@SpringBootApplication(scanBasePackages = "com.crewmate") //해당 api사용할 bean의 scan범위를 상위로 해야 common-api에서 만든 bean을 사용할 수 있다...
public class ShopApiSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApiSystemApplication.class, args);
	}

}
