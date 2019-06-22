package com.example.AWSSpringBoot.AWSSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.example.*")
public class AwsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSpringBootApplication.class, args);
	}
}
