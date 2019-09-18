package com.biocycle.InventoryService.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The Interface ProductStorageMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "productStorageService")
@RibbonClient(name = "productStorageService")
public interface ProductStorageMSProxy {
	
	/**
	 * Gets the optimized space storage containers.
	 *
	 * @param numberOfContainer the number of container
	 * @return the optimized space storage containers
	 */
	@GetMapping(value = "/productstorage/{numberOfContainer}")
	ResponseEntity<List<Integer>> getOptimizedSpaceStorageContainers(@PathVariable("numberOfContainer") int numberOfContainer);
		
}
