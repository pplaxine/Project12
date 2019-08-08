package com.biocycle.productStorageService.bean.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.productStorageService.bean.StorageContainerBean;
import com.biocycle.productStorageService.bean.controller.service.ProductStorageManager;

@RestController
public class ProductStorageController {
	
	@Autowired
	ProductStorageManager productStorageManager;
	
	@GetMapping(value = "/productstorage/{numberOfContainer}")
	Optional<List<Integer>> getContainers(@PathVariable int numberOfContainer){
		return productStorageManager.getOptimizedStorageContainerSpace(numberOfContainer);

	}
}
