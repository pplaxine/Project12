package com.biocycle.entWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;


@FeignClient(name = "zuul-server", contextId = "inventoryservice")
@RibbonClient(name = "inventoryService")
public interface InventoryServiceProxy {
	
	@PostMapping(value = "/inventoryservice/inventory/{numberOfContainer}")
	public ResponseEntity<Void> createEntry(@RequestBody ProductBatchBeanDto productBatchDto, @PathVariable("numberOfContainer") int numberOfContainer);
}
