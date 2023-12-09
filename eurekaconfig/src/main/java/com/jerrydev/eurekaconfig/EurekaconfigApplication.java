package com.jerrydev.eurekaconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaconfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaconfigApplication.class, args);
	}

}
