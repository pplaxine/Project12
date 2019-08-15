package com.biocycle.InventoryService.feign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		//ProductStorageMSProxy
		if(methodKey.equals("ProductStorageMSProxy#getOptimizedSpaceStorageContainers(int)") && response.status() == HttpStatus.NOT_FOUND.value()) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductStorageMSProxy#getOptimizedSpaceStorageContainers(int) replied with status:" + response.status() + "."
					+ "No empty StorageContainer found.");
		}
		if(methodKey.equals("ProductStorageMSProxy#getOptimizedSpaceStorageContainers(int)") && response.status() == HttpStatus.INSUFFICIENT_STORAGE.value()) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductStorageMSProxy#getOptimizedSpaceStorageContainers(int) replied with status:" + response.status() + "."
					+ "There is not enough free storage container space available.");
		}
		
		//ProductBatchCRUDMSProxy
		if(methodKey.equals("ProductBatchCRUDMSProxy#addProductBatch(ProductBatchBeanDto)") && response.status() == HttpStatus.I_AM_A_TEAPOT.value()) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductBatchCRUDMSProxy#addProductBatch(ProductBatchBeanDto) replied "
					+ "ContraintViolationException in :" + response.request().url());
		}
		
		//StorageContainerCRUDMSProxy
		if(methodKey.equals("StorageContainerCRUDMSProxy#getStorageContainers(List)") && response.status() == HttpStatus.NOT_FOUND.value()) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "StorageContainerCRUDMSProxy#getStorageContainers(List) replied with status:"
					+ response.status() + ". No storageContainer found.");
		}
		if(methodKey.equals("StorageContainerCRUDMSProxy#updateStorageContainerList(List)") && response.status() == HttpStatus.I_AM_A_TEAPOT.value()) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "StorageContainerCRUDMSProxy#updateStorageContainerList(List) replied with status:"
					+ "ContraintViolationException in :" + response.request().url());
		}
		
		
		return new Exception(response.reason()); 
	}
	
}
