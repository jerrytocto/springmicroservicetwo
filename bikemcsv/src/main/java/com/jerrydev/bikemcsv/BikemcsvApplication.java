package com.jerrydev.bikemcsv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BikemcsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikemcsvApplication.class, args);
	}
}
