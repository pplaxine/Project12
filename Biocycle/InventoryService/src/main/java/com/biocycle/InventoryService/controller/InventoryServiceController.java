package com.biocycle.InventoryService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.proxy.ProductBatchMSProxy;

@RestController
public class InventoryServiceController {

	@Autowired
	private ProductBatchMSProxy productBatchMSProxy;
	
	@PostMapping(value = "/inventory")
	public ResponseEntity<Void> createEntry(@RequestBody @Valid ProductBatchBean productBatch){
		
		return productBatchMSProxy.addProductBatch(productBatch);
	}
	
}
