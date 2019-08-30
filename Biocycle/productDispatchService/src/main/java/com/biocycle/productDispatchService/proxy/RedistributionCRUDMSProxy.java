package com.biocycle.productDispatchService.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.productDispatchService.dto.RedistributionBeanDto;

@FeignClient(name = "redistributionCRUD")
@RibbonClient(name = "redistributionCRUD")
public interface RedistributionCRUDMSProxy {
	
	@GetMapping(value = "/redistributions/{id}")
	ResponseEntity<RedistributionBeanDto> getRedistributionById(@PathVariable("id") int id);
	
	@PostMapping(value = "/redistributions")
	ResponseEntity<Void> addRedistribution(@RequestBody RedistributionBeanDto redistributionBeanDto);

	@PutMapping(value = "/redistributions")
	void updateRedistribution(@RequestBody RedistributionBeanDto redistributionDto);
	
}
