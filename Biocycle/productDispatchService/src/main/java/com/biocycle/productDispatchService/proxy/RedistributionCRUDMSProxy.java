package com.biocycle.productDispatchService.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.productDispatchService.dto.RedistributionBeanDto;

/**
 * The Interface RedistributionCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "redistributionCRUD")
@RibbonClient(name = "redistributionCRUD")
public interface RedistributionCRUDMSProxy {
	
	/**
	 * Gets the redistribution by id.
	 *
	 * @param id the id
	 * @return the redistribution by id
	 */
	@GetMapping(value = "/redistributions/{id}")
	ResponseEntity<RedistributionBeanDto> getRedistributionById(@PathVariable("id") int id);
	
	/**
	 * Adds the redistribution.
	 *
	 * @param redistributionBeanDto the redistribution bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/redistributions")
	ResponseEntity<Void> addRedistribution(@RequestBody RedistributionBeanDto redistributionBeanDto);

	/**
	 * Update redistribution.
	 *
	 * @param redistributionDto the redistribution dto
	 */
	@PutMapping(value = "/redistributions")
	void updateRedistribution(@RequestBody RedistributionBeanDto redistributionDto);
	
	/**
	 * Delete redistribution.
	 *
	 * @param redistributionId the redistribution id
	 */
	@DeleteMapping(value = "/redistributions/{redistributionId}")
	public void deleteRedistribution (@PathVariable("redistributionId") int redistributionId);
	
}
