package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.customerWebApp.dto.OfferBeanDto;

@FeignClient(name = "zuul-server", contextId = "offercrud")
@RibbonClient(name = "offerCRUD")
public interface OfferCRUDMSProxy {
	
	@GetMapping(value = "/offercrud/offers/{offerId}")
	ResponseEntity<OfferBeanDto> findOfferById(@PathVariable("offerId") int offerId);
}
