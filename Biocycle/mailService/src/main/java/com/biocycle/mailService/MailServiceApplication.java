package com.biocycle.mailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The Class MailServiceApplication.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MailServiceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MailServiceApplication.class, args);
	}

}
