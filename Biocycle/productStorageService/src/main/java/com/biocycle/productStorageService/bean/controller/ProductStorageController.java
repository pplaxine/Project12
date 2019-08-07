package com.biocycle.productStorageService.bean.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductStorageController {
	
	@GetMapping(value = "/productstorage/{numberOfContainer}")
	Optional<List<Integer>> getContainers(@PathVariable int numberOfContainer){
		
		List<Integer> containerIdList = new ArrayList<>(); 
		
		for (int i = 0; i < numberOfContainer; i++) {
			containerIdList.add(15+i);
		}
		
		Optional<List<Integer>> containerIdListOp = Optional.of(containerIdList);
		
		return containerIdListOp; 
	}
}
