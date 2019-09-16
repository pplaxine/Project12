package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;

@FeignClient(name = "zuul-server", contextId = "organisationcrud")
@RibbonClient(name = "organisationCRUD")
public interface OrganisationCRUDMSProxy {
	
	@GetMapping(value = "/organisationcrud/organisations/email/{email}")
	ResponseEntity<OrganisationBeanDto> findOrganisationByEmail(@PathVariable("email") String email);
	
	@PutMapping(value = "/organisationcrud/organisations")
	ResponseEntity<Void> updateOrganisation(@RequestBody OrganisationBeanDto organisationDto);
}
