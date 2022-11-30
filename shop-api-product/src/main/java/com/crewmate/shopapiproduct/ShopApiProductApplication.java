package com.crewmate.shopapiproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.crewmate.shopapicommon.properties.ApplicationProperties;
import com.crewmate.shopapicommon.properties.ExternalApiProperties;

@EnableFeignClients(basePackages = "com.crewmate")
@EnableConfigurationProperties({ApplicationProperties.class, ExternalApiProperties.class})
@SpringBootApplication(scanBasePackages = "com.crewmate") //해당 api사용할 bean의 scan범위를 상위로 해야 common-api에서 만든 bean을 사용할 수 있다...
public class ShopApiProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApiProductApplication.class, args);
	}

}
