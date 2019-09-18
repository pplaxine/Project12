package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.RedistributionBeanDto;

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
	 * Find all active redistributions.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/redistributioncrud/redistributions/active")
	ResponseEntity<List<RedistributionBeanDto>> findAllActiveRedistributions();
	
	/**
	 * Find redistribution by id.
	 *
	 * @param redistributionId the redistribution id
	 * @return the response entity
	 */
	@GetMapping(value = "/redistributioncrud/redistributions/{redistributionId}")
	ResponseEntity<RedistributionBeanDto> findRedistributionById(@PathVariable("redistributionId") int redistributionId);
	
	/**
	 * Update redistribution.
	 *
	 * @param redistributionBeanDto the redistribution bean dto
	 */
	@PutMapping(value = "/redistributioncrud/redistributions")
	public void updateRedistribution(@RequestBody RedistributionBeanDto redistributionBeanDto);

}
