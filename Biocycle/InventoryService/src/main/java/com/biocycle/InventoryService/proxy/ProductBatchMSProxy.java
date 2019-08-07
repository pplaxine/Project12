package com.biocycle.InventoryService.proxy;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.InventoryService.bean.ProductBatchBean;

@FeignClient(name = "productBatchCRUD", url = "localhost:9004")
public interface ProductBatchMSProxy {
	
	@GetMapping(value = "/productbatches/{id}")
	Optional<ProductBatchBean> findProductBatchById(@PathVariable("id") int id);
	
	@PostMapping(value = "/productbatches")
	ResponseEntity<Void> addProductBatch(@RequestBody ProductBatchBean productBatch);
	
	@PutMapping(value = "/productbatches")
	void updateProductBatch(@RequestBody ProductBatchBean productBatch);
	
}
