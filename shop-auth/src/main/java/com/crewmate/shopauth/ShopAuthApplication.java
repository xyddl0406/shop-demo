package com.crewmate.shopauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ShopAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopAuthApplication.class, args);
	}

}
