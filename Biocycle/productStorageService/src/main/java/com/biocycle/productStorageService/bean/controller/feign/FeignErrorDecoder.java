package com.biocycle.productStorageService.bean.controller.feign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		if(methodKey.equals("StorageContainerCRUDMSProxy#findEmptyStorageContainer()") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No empty storageContainer could be found.");
		}
		return new Exception(response.reason()); 
	}
	
}
