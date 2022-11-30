package com.crewmate.shopauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.crewmate.shopapicommon.properties.ApplicationProperties;
import com.crewmate.shopapicommon.properties.ExternalApiProperties;

@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //common-api에 의존되어있는 database 설정bean 이 동작하지 않게하는 제외설정
@EnableConfigurationProperties({ApplicationProperties.class, ExternalApiProperties.class}) //application.yml에 설정한 변수를 javaconfig로 만들면 이렇게 선언해줘야 해당빈을 찾을 수 있다. 
public class ShopAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopAuthApplication.class, args);
	}

}
