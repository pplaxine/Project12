package com.biocycle.customerWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.biocycle.customerWebApp.dto.ProductBatchBeanDto;

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
	 * @param productBatchId the product batch id
	 * @return the product batch bean dto
	 */
	@GetMapping(value = "/productbatchcrud/productbatches/{productBatchId}")
	ProductBatchBeanDto findProductBatchById(@PathVariable("productBatchId") int productBatchId);
	
	/**
	 * Gets the product soon to expire.
	 *
	 * @return the product soon to expire
	 */
	@GetMapping(value = "/productbatchcrud/productbatches/soon/expired")
	ResponseEntity<List<ProductBatchBeanDto>> getProductSoonToExpire();
	
	/**
	 * Update product batch is awaiting to be collected status.
	 *
	 * @param productBatchId the product batch id
	 * @param status the status
	 * @return the response entity
	 */
	@PutMapping(value = "/productbatchcrud/productbatches/is-awaiting-for-collection/{productBatchId}/{status}")
	ResponseEntity<Void> updateProductBatchIsAwaitingToBeCollectedStatus(@PathVariable int productBatchId, @PathVariable Boolean status);
}
