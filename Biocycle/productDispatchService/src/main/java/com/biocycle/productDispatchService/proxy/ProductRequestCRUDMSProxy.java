package com.biocycle.productDispatchService.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;

/**
 * The Interface ProductRequestCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "productRequestCRUD")
@RibbonClient(name = "productRequestCRUD")
public interface ProductRequestCRUDMSProxy {
	
	/**
	 * Adds the product request list.
	 *
	 * @param productRequestBeanDtoList the product request bean dto list
	 * @return the response entity
	 */
	@PostMapping(value = "/productrequests/list")
	ResponseEntity<List<ProductRequestBeanDto>> addProductRequestList(@RequestBody List<ProductRequestBeanDto> productRequestBeanDtoList); 
	
	/**
	 * Delete product request list.
	 *
	 * @param productRequestIdList the product request id list
	 * @return the response entity
	 */
	@DeleteMapping(value = "/productrequests/list")
	ResponseEntity<Void> deleteProductRequestList(@RequestParam("productRequestIdList") Integer[]productRequestIdList);
}
