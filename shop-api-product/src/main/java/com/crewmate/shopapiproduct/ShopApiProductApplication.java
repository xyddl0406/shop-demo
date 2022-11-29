package com.crewmate.shopapiproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.crewmate.shopapicommon.config.ApplicationProperties;
import com.crewmate.shopapicommon.config.ExternalApiConfig;

@EnableConfigurationProperties({ApplicationProperties.class, ExternalApiConfig.class})
@SpringBootApplication(scanBasePackages = "com.crewmate") //해당 api사용할 bean의 scan범위를 상위로 해야 common-api에서 만든 bean을 사용할 수 있다...
public class ShopApiProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApiProductApplication.class, args);
	}

}
