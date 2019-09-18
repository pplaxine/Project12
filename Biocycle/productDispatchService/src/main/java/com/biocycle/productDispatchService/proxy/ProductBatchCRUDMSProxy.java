package com.biocycle.productDispatchService.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


/**
 * The Interface ProductBatchCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server")
@RibbonClient(name = "productBatchCRUD")
public interface ProductBatchCRUDMSProxy {

	/**
	 * Update product batch is awaiting to be collected status.
	 *
	 * @param productBatchId the product batch id
	 * @param status the status
	 * @return the response entity
	 */
	@PutMapping(value = "productbatchcrud/productbatches/is-awaiting-for-collection/{productBatchId}/{status}")
	ResponseEntity<Void> updateProductBatchIsAwaitingToBeCollectedStatus(@PathVariable("productBatchId") int productBatchId, @PathVariable Boolean status);
	
	/**
	 * Delete product batch.
	 *
	 * @param productBatchId the product batch id
	 */
	@DeleteMapping(value = "/productbatches/{productBatchId}")
	void deleteProductBatch(@PathVariable("productBatchId") int productBatchId);
}
