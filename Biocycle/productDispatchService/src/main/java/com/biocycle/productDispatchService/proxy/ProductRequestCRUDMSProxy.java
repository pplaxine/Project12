package com.biocycle.productDispatchService.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;

@FeignClient(name = "productRequestCRUD", url = "localhost:9006")
public interface ProductRequestCRUDMSProxy {
	
	@PostMapping(value = "/productrequests/list")
	ResponseEntity<List<ProductRequestBeanDto>> addProductRequestList(@RequestBody List<ProductRequestBeanDto> productRequestBeanDtoList); 
	
	@DeleteMapping(value = "/productrequests/list")
	ResponseEntity<Void> deleteProductRequestList(@RequestParam("productRequestIdList") Integer[]productRequestIdList);
}
