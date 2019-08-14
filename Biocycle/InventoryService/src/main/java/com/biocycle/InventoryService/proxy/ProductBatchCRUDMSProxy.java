package com.biocycle.InventoryService.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.InventoryService.dto.ProductBatchBeanDto;

@FeignClient(name = "productBatchCRUD")
@RibbonClient(name = "productBatchCRUD")
public interface ProductBatchCRUDMSProxy {
	
	@GetMapping(value = "/productbatches/{id}")
	ProductBatchBeanDto findProductBatchById(@PathVariable("id") int id);
	
	@PostMapping(value = "/productbatches")
	ResponseEntity<Void> addProductBatch(@RequestBody ProductBatchBeanDto productBatchBeanDto);
	
	@PutMapping(value = "/productbatches")
	void updateProductBatch(@RequestBody ProductBatchBeanDto productBatchBeanDto);
	
}
