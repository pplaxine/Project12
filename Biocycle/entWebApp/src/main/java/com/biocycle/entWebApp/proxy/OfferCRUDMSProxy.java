package com.biocycle.entWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.OfferBeanDto;

@FeignClient(name = "zuul-server", contextId = "offercrud")
@RibbonClient(name = "offerCRUD")
public interface OfferCRUDMSProxy {
	
	@GetMapping(value = "/offercrud//offers/{offerId}")
	ResponseEntity<OfferBeanDto> findOfferById(@PathVariable int offerId);
	
	@PostMapping(value = "/offercrud/offers")
	ResponseEntity<Void> updateOffer(@RequestBody OfferBeanDto offerBeanDto);
}
