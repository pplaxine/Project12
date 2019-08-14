package com.biocycle.InventoryService.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.InventoryService.bean.ProductBatchBean;

@FeignClient(name = "productStorageService")
@RibbonClient(name = "productStorageService")
public interface ProductStorageMSProxy {
	
	@GetMapping(value = "/productstorage/{numberOfContainer}")
	ResponseEntity<List<Integer>> getOptimizedSpaceStorageContainers(@PathVariable("numberOfContainer") int numberOfContainer);
		
}
