package com.biocycle.collectionManagmentService.dto.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "collectionRunCRUD", url = "localhost:9003")
public interface CollectionRunCRUDMSProxy {
	
	
	
}
