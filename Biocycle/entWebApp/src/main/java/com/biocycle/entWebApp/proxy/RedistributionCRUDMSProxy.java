package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.entWebApp.dto.RedistributionBeanDto;

@FeignClient(name = "zuul-server", contextId = "redistributioncrud")
@RibbonClient(name = "redistributionCRUD")
public interface RedistributionCRUDMSProxy {
	
	@GetMapping(value = "/redistributioncrud/redistributions/active")
	ResponseEntity<List<RedistributionBeanDto>> findAllActiveRedistributions();
	
	@GetMapping(value = "/redistributioncrud/redistributions/{redistributionId}")
	ResponseEntity<RedistributionBeanDto> findRedistributionById(@PathVariable("redistributionId") int redistributionId);

}
