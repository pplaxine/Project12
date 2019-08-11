package com.biocycle.giveAwayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.biocycle")
@EnableDiscoveryClient
public class GiveAwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiveAwayServiceApplication.class, args);
	}

}
