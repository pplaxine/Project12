package com.biocycle.collectionManagmentService.dto.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "collectionRunCRUD")
@RibbonClient(name = "collectionRunCRUD")
public interface CollectionRunCRUDMSProxy {
	
	
	
}
