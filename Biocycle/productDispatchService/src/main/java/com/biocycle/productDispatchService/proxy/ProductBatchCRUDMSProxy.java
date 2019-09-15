package com.biocycle.productDispatchService.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "zuul-server")
@RibbonClient(name = "productBatchCRUD")
public interface ProductBatchCRUDMSProxy {

	@PutMapping(value = "productbatchcrud/productbatches/is-awaiting-for-collection/{productBatchId}/{status}")
	ResponseEntity<Void> updateProductBatchIsAwaitingToBeCollectedStatus(@PathVariable("productBatchId") int productBatchId, @PathVariable Boolean status);
	
	@DeleteMapping(value = "/productbatches/{productBatchId}")
	void deleteProductBatch(@PathVariable("productBatchId") int productBatchId);
}
