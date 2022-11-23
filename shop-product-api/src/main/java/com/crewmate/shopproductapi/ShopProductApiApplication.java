package com.crewmate.shopproductapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.crewmate") //해당 api사용할 bean의 scan범위를 상위로 해야 common-api에서 만든 bean을 사용할 수 있다...
public class ShopProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopProductApiApplication.class, args);
	}

}
