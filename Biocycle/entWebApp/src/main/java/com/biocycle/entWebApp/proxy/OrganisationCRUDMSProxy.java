package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.OrganisationBeanDto;

@FeignClient(name = "zuul-server", contextId = "organisationcrud")
@RibbonClient(name = "organisationCRUD")
public interface OrganisationCRUDMSProxy {

	@GetMapping(value = "/organisationcrud/organisations/validated/{isValidated}")
	ResponseEntity<List<OrganisationBeanDto>>  findAllOrganisationByIsValidated(@PathVariable("isValidated") boolean isValidated);
	
	@PutMapping(value = "/organisationcrud/organisations")
	ResponseEntity<Void> updateOrganisation(@RequestBody OrganisationBeanDto organisationBeanDto);
	
	@GetMapping(value = "/organisationcrud/organisations/{id}")
	ResponseEntity<OrganisationBeanDto> findOrganisationById(@PathVariable("id") int id);
}
