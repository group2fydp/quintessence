package com.cove.safetyplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class SafetyplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyplanApplication.class, args);
	}

}
