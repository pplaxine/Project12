package com.biocycle.productRequestCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The Class ProductRequestCrudApplication.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductRequestCrudApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductRequestCrudApplication.class, args);
	}

}
