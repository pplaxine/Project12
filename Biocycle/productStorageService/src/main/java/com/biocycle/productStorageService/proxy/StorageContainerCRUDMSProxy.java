package com.biocycle.productStorageService.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.biocycle.productStorageService.bean.StorageContainerBean;

/**
 * The Interface StorageContainerCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "storageContainerCRUD")
@RibbonClient(name = "storageContainerCRUD")
public interface StorageContainerCRUDMSProxy {
	
	/**
	 * Find empty storage container.
	 *
	 * @return the optional
	 */
	@GetMapping(value = "/storagecontainers/empty")
	Optional<List<StorageContainerBean>> findEmptyStorageContainer();
}
