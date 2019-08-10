package com.biocycle.customerManagmentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.biocycle")
@EnableDiscoveryClient
public class FunctonalitiesRelatedOrganisationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctonalitiesRelatedOrganisationsApplication.class, args);
	}

}
