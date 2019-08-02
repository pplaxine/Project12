package com.biocycle.staffCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StaffCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffCrudApplication.class, args);
	}

}
