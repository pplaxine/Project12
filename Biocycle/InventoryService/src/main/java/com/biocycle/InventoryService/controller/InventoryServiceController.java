package com.biocycle.InventoryService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.InventoryService.dto.ProductBatchDto;
import com.biocycle.InventoryService.service.ProductBatchManager;

/**
 * The Class InventoryServiceController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@RestController
public class InventoryServiceController {

	/** The productbatch manager. */
	@Autowired
	private ProductBatchManager productbatchManager;
	
	/**
	 * Creates the entry.
	 *
	 * @param productBatchDto the product batch dto
	 * @param numberOfContainer the number of container
	 * @return the response entity
	 */
	@PostMapping(value = "/inventory/{numberOfContainer}")
	public ResponseEntity<Void> createEntry(@RequestBody @Valid ProductBatchDto productBatchDto, @PathVariable int numberOfContainer){
		return productbatchManager.createProductBatch(productBatchDto, numberOfContainer);
	}
	
}
