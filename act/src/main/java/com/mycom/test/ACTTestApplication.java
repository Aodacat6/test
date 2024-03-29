package com.mycom.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(value = "com.mycom")
@MapperScan("com.mycom.test.mapper")
@EnableFeignClients(basePackages = "com.mycom.test.client")
@SpringBootApplication(exclude = {
		org.activiti.spring.boot.SecurityAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class ACTTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ACTTestApplication.class, args);
	}

}
