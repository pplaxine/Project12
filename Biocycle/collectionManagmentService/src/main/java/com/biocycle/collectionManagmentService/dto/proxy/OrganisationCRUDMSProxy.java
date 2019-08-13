package com.biocycle.collectionManagmentService.dto.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.collectionManagmentService.dto.OrganisationBeanDto;

@FeignClient(name = "organisationCRUD", url = "localhost:9001")
public interface OrganisationCRUDMSProxy {
	
	@GetMapping(value = "/organisations/{id}")
	ResponseEntity<OrganisationBeanDto> getOrgnisationBeanById(@PathVariable("id") int id);
}
