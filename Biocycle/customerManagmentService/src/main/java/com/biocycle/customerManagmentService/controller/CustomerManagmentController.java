package com.biocycle.customerManagmentService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;
import com.biocycle.customerManagmentService.exception.OrganisationNotFoundException;
import com.biocycle.customerManagmentService.proxy.OrganisationCRUDMSProxy;

@RestController
public class CustomerManagmentController {

	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	
	//---- GET 
	@GetMapping(value = "/organisations")
	public ResponseEntity<List<OrganisationBeanDto>> findAllOrganisation(){
		
		ResponseEntity<List<OrganisationBeanDto>> organisationBeanDtoListResp = organisationCRUDMSProxy.findAllOrganisation();
		
		if(organisationBeanDtoListResp.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new OrganisationNotFoundException("No organisation could be found.");
		}
		if(organisationBeanDtoListResp.getStatusCode() != HttpStatus.OK) {
			return ResponseEntity.status(organisationBeanDtoListResp.getStatusCode()).build();
		}
		return organisationBeanDtoListResp;
	}
}
