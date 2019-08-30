package com.biocycle.entWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.OfferBeanDto;

@FeignClient(name = "zuul-server", contextId = "productdispatchservice")
@RibbonClient(name = "productDispatchService")
public interface ProductDispatchServiceProxy {

	@PutMapping(value = "productdispatchservice/redistributions/offers/add/{redistributionId}")
	ResponseEntity<Void> updateRedistributionWithoffer(@PathVariable("redistributionId") int redistributionId, @RequestBody OfferBeanDto offerBeanDto);
}
