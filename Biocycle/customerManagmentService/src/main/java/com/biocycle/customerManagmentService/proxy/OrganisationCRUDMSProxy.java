package com.biocycle.customerManagmentService.proxy;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;

@FeignClient(name = "organisationCRUD", url = "localhost:9001")
public interface OrganisationCRUDMSProxy {
	
	@GetMapping(value = "/organisations")
	ResponseEntity<List<OrganisationBeanDto>> findAllOrganisation();
}
