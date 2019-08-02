package com.biocycle.storageContainerCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StorageContainerCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(StorageContainerCrudApplication.class, args);
	}

}
