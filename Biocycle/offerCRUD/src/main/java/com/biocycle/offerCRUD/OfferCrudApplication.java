package com.biocycle.offerCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OfferCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferCrudApplication.class, args);
	}

}
