package com.mycom.nodbweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.mycom.nodbweb.client")
@SpringBootApplication
public class NoDbWebTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoDbWebTestApplication.class, args);
	}

}
