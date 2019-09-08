package com.biocycle.customerManagmentService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;
import com.biocycle.customerManagmentService.proxy.OrganisationCRUDMSProxy;
import com.biocycle.customerManagmentService.service.OrganisationManager;

@RestController
public class CustomerManagmentController {

	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@Autowired
	private OrganisationManager organisationManager;
	
	//---- GET 
	@GetMapping(value = "/organisations")
	public ResponseEntity<List<OrganisationBeanDto>> findAllOrganisation(){
		
		ResponseEntity<List<OrganisationBeanDto>> resp = organisationCRUDMSProxy.findAllOrganisation();
		
		return resp;
	}
	
	//---- POST 
	@PostMapping(value = "/organisations")
	public ResponseEntity<Void> addOrganisation(@RequestBody OrganisationBeanDto organisationBeanDto){
		return organisationManager.addOrganisation(organisationBeanDto);
	}
	
	@PostMapping(value = "/organisations/password")
	public ResponseEntity<Void> addPassword(@RequestBody OrganisationBeanDto organisationBeanDto){
		return organisationManager.addPassword(organisationBeanDto);
	}
	
	
	
	
}
