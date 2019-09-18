package com.biocycle.productDispatchService.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.productDispatchService.dto.OfferBeanDto;

/**
 * The Interface OfferCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "offerCRUD")
@RibbonClient(name = "offerCRUD")
public interface OfferCRUDMSProxy {
	
	/**
	 * Adds the offer.
	 *
	 * @param offerBeanDto the offer bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/offers")
	ResponseEntity<Void> addOffer(@RequestBody OfferBeanDto offerBeanDto);
	
	/**
	 * Delete offer.
	 *
	 * @param offerId the offer id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/offers/{id}")
	ResponseEntity<Void> deleteOffer(@PathVariable("id") int offerId);
}
