package com.biocycle.productDispatchService.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.productDispatchService.dto.OfferBeanDto;

@FeignClient(name = "offerCRUD", url = "localhost:9008")
public interface OfferCRUDMSProxy {
	
	@PostMapping(value = "/offers")
	ResponseEntity<Void> addOffer(@RequestBody OfferBeanDto offerBeanDto);
	
	@DeleteMapping(value = "/offers/{id}")
	ResponseEntity<Void> deleteOffer(@PathVariable("id") int offerId);
}
