package com.biocycle.productStorageService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The Class ProductStorageServiceApplication.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ProductStorageServiceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductStorageServiceApplication.class, args);
	}

}
