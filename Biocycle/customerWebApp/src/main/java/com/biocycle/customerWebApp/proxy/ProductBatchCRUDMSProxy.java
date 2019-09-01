package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.biocycle.customerWebApp.dto.ProductBatchBeanDto;

@FeignClient(name = "zuul-server", contextId = "productbatchcrud")
@RibbonClient(name = "productBatchCRUD")
public interface ProductBatchCRUDMSProxy {
	
	@GetMapping(value = "/productbatchcrud/productbatches/{productBatchId}")
	ProductBatchBeanDto findProductBatchById(@PathVariable("productBatchId") int productBatchId);
	
	@PutMapping(value = "/productbatchcrud/productbatches/is-awaiting-for-collection/{productBatchId}/{status}")
	ResponseEntity<Void> updateProductBatchIsAwaitingToBeCollectedStatus(@PathVariable int productBatchId, @PathVariable Boolean status);
}
