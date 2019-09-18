package com.biocycle.customerWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;

/**
 * The Interface ProductDispatchServiceProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "productdispatchservice")
@RibbonClient(name = "productDispatchService")
public interface ProductDispatchServiceProxy {
	
	/**
	 * Adds the redistribution for request.
	 *
	 * @param organisationId the organisation id
	 * @param productRequestBeanDtoList the product request bean dto list
	 * @return the response entity
	 */
	@PostMapping(value = "/productdispatchservice/redistributions/requests/{organisationId}")
	ResponseEntity<Void> addRedistributionForRequest(@PathVariable("organisationId") int organisationId, @RequestBody List<ProductRequestBeanDto> productRequestBeanDtoList);
}
