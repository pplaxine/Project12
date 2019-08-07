package com.biocycle.InventoryService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.proxy.ProductBatchMSProxy;
import com.biocycle.InventoryService.service.ProductBatchManager;

	

@RestController
public class InventoryServiceController {

	@Autowired
	private ProductBatchManager productbatchManager;
	
	@PostMapping(value = "/inventory/{numberOfContainer}")
	public ResponseEntity<Void> createEntry(@RequestBody @Valid ProductBatchBean productBatch, @PathVariable int numberOfContainer){
		return productbatchManager.createProductBatch(productBatch, numberOfContainer);
	}
	
}
