package com.biocycle.productStorageService.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.productStorageService.service.ProductStorageManager;

/**
 * The Class ProductStorageController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@RestController
public class ProductStorageController {
	
	/** The product storage manager. */
	@Autowired
	ProductStorageManager productStorageManager;
	
	/**
	 * Gets the containers.
	 *
	 * @param numberOfContainer the number of container
	 * @return the containers
	 */
	@GetMapping(value = "/productstorage/{numberOfContainer}")
	ResponseEntity<List<Integer>> getContainers(@PathVariable int numberOfContainer){
		return productStorageManager.getOptimizedStorageContainerSpace(numberOfContainer);

	}
}
