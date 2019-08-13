package com.biocycle.productDispatchService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.biocycle")
@EnableDiscoveryClient
public class ProductDispatchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDispatchServiceApplication.class, args);
	}

}
