package com.biocycle.customerWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.customerWebApp.dto.RedistributionBeanDto;

@FeignClient(name = "zuul-server", contextId = "redistributioncrud")
@RibbonClient(name = "redistributionCRUD")
public interface RedistributionCRUDMSProxy {
	
	@GetMapping(value = "/redistributioncrud/redistributions/{id}")
	ResponseEntity<RedistributionBeanDto> getRedistributionById(@PathVariable("id") int id);
	
	@GetMapping(value = "/redistributioncrud/redistributions/organisations/{organisationId}")
	ResponseEntity<List<RedistributionBeanDto>> getAllRedistributionByOrganisationId(@PathVariable("organisationId") int organisationId);
}
