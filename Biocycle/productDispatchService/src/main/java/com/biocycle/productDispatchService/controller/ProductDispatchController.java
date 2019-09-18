package com.biocycle.productDispatchService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.productDispatchService.dto.OfferBeanDto;
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;
import com.biocycle.productDispatchService.service.ProductDispatchManager;

/**
 * The Class ProductDispatchController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@RestController
public class ProductDispatchController {

	/** The product dispatch manager. */
	@Autowired
	private ProductDispatchManager productDispatchManager;
	
	/**
	 * Adds the redistribution for request.
	 *
	 * @param organisationId the organisation id
	 * @param productRequestBeanDtoList the product request bean dto list
	 * @return the response entity
	 */
	//--- POST
	@PostMapping(value = "/redistributions/requests/{organisationId}")
	public ResponseEntity<Void> addRedistributionForRequest(@PathVariable int organisationId, @RequestBody List<ProductRequestBeanDto> productRequestBeanDtoList){
		return  productDispatchManager.createRedistributionForRequest(organisationId, productRequestBeanDtoList);
	}
	
	/**
	 * Adds the redistribution for offer.
	 *
	 * @param organisationId the organisation id
	 * @param offerBeanDto the offer bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/redistributions/offers/{organisationId}")
	public ResponseEntity<Void> addRedistributionForOffer(@PathVariable int organisationId, @RequestBody OfferBeanDto offerBeanDto){
		return  productDispatchManager.createRedistributionForOffer(organisationId, offerBeanDto);
	}
	
	/**
	 * Update redistribution with offer.
	 *
	 * @param redistributionId the redistribution id
	 * @param offerBeanDto the offer bean dto
	 * @return the response entity
	 */
	@PutMapping(value = "/redistributions/offers/add/{redistributionId}")
	public ResponseEntity<Void> updateRedistributionWithoffer(@PathVariable int redistributionId, @RequestBody OfferBeanDto offerBeanDto){
		return productDispatchManager.addOfferToRedistribution(redistributionId, offerBeanDto);
	}
	
}
