package com.biocycle.giveAwayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The Class GiveAwayServiceApplication.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients("com.biocycle")
@EnableDiscoveryClient
public class GiveAwayServiceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(GiveAwayServiceApplication.class, args);
	}

}
