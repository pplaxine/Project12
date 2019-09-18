package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;

/**
 * The Interface ProductBatchCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "productbatchcrud")
@RibbonClient(name = "productBatchCRUD")
public interface ProductBatchCRUDMSProxy {
	
	/**
	 * Find product batch by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping(value = "/productbatchcrud/productbatches/{id}")
	ResponseEntity<ProductBatchBeanDto> findProductBatchById(@PathVariable("id") int id);

	/**
	 * Find all product batch.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/productbatchcrud/productbatches")
	ResponseEntity<List<ProductBatchBeanDto>> findAllProductBatch();
	
	/**
	 * Update product batch is awaiting to be collected status.
	 *
	 * @param productBatchId the product batch id
	 * @param status the status
	 * @return the response entity
	 */
	@PutMapping(value = "/productbatchcrud/productbatches/is-awaiting-for-collection/{productBatchId}/{status}")
	ResponseEntity<Void> updateProductBatchIsAwaitingToBeCollectedStatus(@PathVariable int productBatchId, @PathVariable Boolean status);
	
	/**
	 * Update product batch.
	 *
	 * @param productBatchBeanDto the product batch bean dto
	 */
	@PutMapping(value = "/productbatchcrud/productbatches")
	void updateProductBatch(@RequestBody ProductBatchBeanDto productBatchBeanDto);

	/**
	 * Delete product batch.
	 *
	 * @param productBatchId the product batch id
	 */
	@DeleteMapping(value = "/productbatchcrud/productbatches/{productBatchId}")
	public void deleteProductBatch(@PathVariable int productBatchId);
}
