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

@RestController
public class ProductDispatchController {

	@Autowired
	private ProductDispatchManager productDispatchManager;
	
	//--- POST
	@PostMapping(value = "/redistributions/requests/{organisationId}")
	public ResponseEntity<Void> addRedistributionForRequest(@PathVariable int organisationId, @RequestBody List<ProductRequestBeanDto> productRequestBeanDtoList){
		return  productDispatchManager.createRedistributionForRequest(organisationId, productRequestBeanDtoList);
	}
	
	@PostMapping(value = "/redistributions/offers/{organisationId}")
	public ResponseEntity<Void> addRedistributionForOffer(@PathVariable int organisationId, @RequestBody OfferBeanDto offerBeanDto){
		return  productDispatchManager.createRedistributionForOffer(organisationId, offerBeanDto);
	}
	
	@PutMapping(value = "/redistributions/offers/add/{redistributionId}")
	public ResponseEntity<Void> updateRedistributionWithoffer(@PathVariable int redistributionId, @RequestBody OfferBeanDto offerBeanDto){
		return productDispatchManager.addOfferToRedistribution(redistributionId, offerBeanDto);
	}
	
}
