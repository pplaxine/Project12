package com.biocycle.productDispatchService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The Class ProductDispatchServiceApplication.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients("com.biocycle")
@EnableDiscoveryClient
public class ProductDispatchServiceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductDispatchServiceApplication.class, args);
	}

}
