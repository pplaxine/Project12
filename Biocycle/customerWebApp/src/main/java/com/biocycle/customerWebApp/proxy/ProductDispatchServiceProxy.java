package com.biocycle.customerWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;

@FeignClient(name = "zuul-server", contextId = "productdispatchservice")
@RibbonClient(name = "productDispatchService")
public interface ProductDispatchServiceProxy {
	
	@PostMapping(value = "/productdispatchservice/redistributions/requests/{organisationId}")
	ResponseEntity<Void> addRedistributionForRequest(@PathVariable("organisationId") int organisationId, @RequestBody List<ProductRequestBeanDto> productRequestBeanDtoList);
}
