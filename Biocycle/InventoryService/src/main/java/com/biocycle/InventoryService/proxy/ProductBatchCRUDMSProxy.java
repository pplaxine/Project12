package com.biocycle.InventoryService.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.InventoryService.dto.ProductBatchBeanDto;

/**
 * The Interface ProductBatchCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "productBatchCRUD")
@RibbonClient(name = "productBatchCRUD")
public interface ProductBatchCRUDMSProxy {
	
	/**
	 * Find product batch by id.
	 *
	 * @param id the id
	 * @return the product batch bean dto
	 */
	@GetMapping(value = "/productbatches/{id}")
	ProductBatchBeanDto findProductBatchById(@PathVariable("id") int id);
	
	/**
	 * Adds the product batch.
	 *
	 * @param productBatchBeanDto the product batch bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/productbatches")
	ResponseEntity<Void> addProductBatch(@RequestBody ProductBatchBeanDto productBatchBeanDto);
	
	/**
	 * Update product batch.
	 *
	 * @param productBatchBeanDto the product batch bean dto
	 */
	@PutMapping(value = "/productbatches")
	void updateProductBatch(@RequestBody ProductBatchBeanDto productBatchBeanDto);
	
	/**
	 * Delete product batch.
	 *
	 * @param id the id
	 */
	@DeleteMapping(value = "/productbatches/{id}")
	void deleteProductBatch(@PathVariable("id") int id);
	
}
