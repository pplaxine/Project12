package com.biocycle.InventoryService.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.InventoryService.bean.ProductBatchBean;

@FeignClient(name = "productStorageService", url = "localhost:9505")
public interface ProductStorageMSProxy {
	
	@GetMapping(value = "/productstorage/{numberOfContainer}")
	Optional<List<Integer>> getContainers(@PathVariable("numberOfContainer") int numberOfContainer);
		
}
