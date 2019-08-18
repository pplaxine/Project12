package com.biocycle.customerManagmentService.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;

@FeignClient(name = "organisationCRUD")
@RibbonClient(name = "organisationCRUD")
public interface OrganisationCRUDMSProxy {
	
	@GetMapping(value = "/organisations")
	ResponseEntity<List<OrganisationBeanDto>> findAllOrganisation();
	
	@GetMapping(value="/organisations/{id}")
	ResponseEntity<OrganisationBeanDto> findOrganisationById(@PathVariable("id") int id);
	
	@GetMapping(value = "/organisations/email/{email}")
	ResponseEntity<OrganisationBeanDto> findOrganisationByEmail(@PathVariable("email") String email);
	
	@PostMapping(value = "/organisations")
	ResponseEntity<Void> addOrganisation(@RequestBody OrganisationBeanDto organisationBeanDto);
	
	@PutMapping(value =  "/organisations")
	ResponseEntity<Void> updateOrganisation(@RequestBody OrganisationBeanDto organisationBeanDto);
	
}
