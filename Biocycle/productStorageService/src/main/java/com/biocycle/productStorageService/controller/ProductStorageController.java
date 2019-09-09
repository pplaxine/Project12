package com.biocycle.productStorageService.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.productStorageService.service.ProductStorageManager;

@RestController
public class ProductStorageController {
	
	@Autowired
	ProductStorageManager productStorageManager;
	
	@GetMapping(value = "/productstorage/{numberOfContainer}")
	ResponseEntity<List<Integer>> getContainers(@PathVariable int numberOfContainer){
		return productStorageManager.getOptimizedStorageContainerSpace(numberOfContainer);

	}
}
