package com.biocycle.organisationCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.biocycle.organisationCRUD.dao.OrganisationDao;
import com.biocycle.organisationCRUD.model.Address;
import com.biocycle.organisationCRUD.model.Organisation;

@SpringBootApplication
@EnableDiscoveryClient
public class OrganisationCrudApplication {

	@Autowired
	static OrganisationDao organisationDao;
	
	public static void main(String[] args) {
		SpringApplication.run(OrganisationCrudApplication.class, args);
	}

}
