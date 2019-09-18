package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.OfferBeanDto;

/**
 * The Interface OfferCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "offercrud")
@RibbonClient(name = "offerCRUD")
public interface OfferCRUDMSProxy {
	
	/**
	 * Find offer by id.
	 *
	 * @param offerId the offer id
	 * @return the response entity
	 */
	@GetMapping(value = "/offercrud/offers/{offerId}")
	ResponseEntity<OfferBeanDto> findOfferById(@PathVariable("offerId") int offerId);
	
	/**
	 * Update offer.
	 *
	 * @param offerBeanDto the offer bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/offercrud/offers")
	ResponseEntity<Void> updateOffer(@RequestBody OfferBeanDto offerBeanDto);
}
