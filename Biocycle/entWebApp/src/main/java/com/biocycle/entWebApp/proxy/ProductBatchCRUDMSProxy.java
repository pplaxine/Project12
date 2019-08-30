package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;

@FeignClient(name = "zuul-server", contextId = "productbatchcrud")
@RibbonClient(name = "productBatchCRUD")
public interface ProductBatchCRUDMSProxy {
	
	@GetMapping(value = "/productbatchcrud/productbatches/{id}")
	ResponseEntity<ProductBatchBeanDto> findProductBatchById(@PathVariable("id") int id);

	@GetMapping(value = "/productbatchcrud/productbatches")
	ResponseEntity<List<ProductBatchBeanDto>> findAllProductBatch();
	
	
}
