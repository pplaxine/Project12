package com.biocycle.productBatchCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductBatchCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductBatchCrudApplication.class, args);
	}

}
