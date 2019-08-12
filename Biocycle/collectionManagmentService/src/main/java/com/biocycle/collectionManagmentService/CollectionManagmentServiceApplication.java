package com.biocycle.collectionManagmentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.biocycle")
@EnableDiscoveryClient
public class CollectionManagmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectionManagmentServiceApplication.class, args);
	}

}
