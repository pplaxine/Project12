package com.biocycle.productStorageService.bean.controller.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.biocycle.productStorageService.bean.StorageContainerBean;

@FeignClient(name = "storageContainerCRUD")
@RibbonClient(name = "storageContainerCRUD")
public interface StorageContainerCRUDMSProxy {
	
	@GetMapping(value = "/storagecontainers/empty")
	Optional<List<StorageContainerBean>> findEmptyStorageContainer();
}
