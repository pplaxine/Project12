package com.biocycle.customerWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.RedistributionBeanDto;

/**
 * The Interface RedistributionCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "redistributioncrud")
@RibbonClient(name = "redistributionCRUD")
public interface RedistributionCRUDMSProxy {
	
	/**
	 * Gets the redistribution by id.
	 *
	 * @param id the id
	 * @return the redistribution by id
	 */
	@GetMapping(value = "/redistributioncrud/redistributions/{id}")
	ResponseEntity<RedistributionBeanDto> getRedistributionById(@PathVariable("id") int id);
	
	/**
	 * Gets the all redistribution by organisation id.
	 *
	 * @param organisationId the organisation id
	 * @return the all redistribution by organisation id
	 */
	@GetMapping(value = "/redistributioncrud/redistributions/organisations/{organisationId}")
	ResponseEntity<List<RedistributionBeanDto>> getAllRedistributionByOrganisationId(@PathVariable("organisationId") int organisationId);
	
	/**
	 * Update redistribution.
	 *
	 * @param redistributionDto the redistribution dto
	 */
	@PutMapping(value = "redistributioncrud/redistributions")
	void updateRedistribution(@RequestBody RedistributionBeanDto redistributionDto);
}
