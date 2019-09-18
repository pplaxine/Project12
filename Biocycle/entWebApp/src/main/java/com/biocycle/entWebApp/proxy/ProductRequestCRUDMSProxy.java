package com.biocycle.entWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.entWebApp.dto.ProductRequestBeanDto;

/**
 * The Interface ProductRequestCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "productrequestcrud")
@RibbonClient(name = "productRequestCRUD")
public interface ProductRequestCRUDMSProxy {
	
	/**
	 * Find product request by id.
	 *
	 * @param id the id
	 * @return the product request bean dto
	 */
	@GetMapping(value = "/productrequestcrud/productrequests/{id}")
	ProductRequestBeanDto findProductRequestById(@PathVariable int id);

}
