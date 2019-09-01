package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;

@FeignClient(name = "zuul-server", contextId = "productrequestcrud")
@RibbonClient(name = "productRequestCRUD")
public interface ProductRequestCRUDMSProxy {
	
	@GetMapping(value = "/productrequestcrud/productrequests/{id}")
	ProductRequestBeanDto findProductRequestById(@PathVariable("id") int id);
	
	@PutMapping(value = "/productrequestcrud/productrequests")
	void updateProductRequest(@RequestBody ProductRequestBeanDto productRequestDto);
}
