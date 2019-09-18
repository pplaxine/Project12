package com.biocycle.InventoryService.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.biocycle.InventoryService.dto.StorageContainerBeanDto;

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
	 * Gets the storage containers.
	 *
	 * @param containerIdList the container id list
	 * @return the storage containers
	 */
	@GetMapping(value = "/storagecontainers/listId")
	Optional<List<StorageContainerBeanDto>> getStorageContainers(@RequestParam("containerIdList") Integer[]containerIdList);
	
	/**
	 * Update storage container list.
	 *
	 * @param storageContainerBeanDtoList the storage container bean dto list
	 * @return the response entity
	 */
	@PutMapping(value = "/storagecontainers/updatestatus")
	ResponseEntity<Void> updateStorageContainerList(@RequestBody List<StorageContainerBeanDto> storageContainerBeanDtoList);
	
	/**
	 * Update storage container.
	 *
	 * @param storageContainerDto the storage container dto
	 * @return the response entity
	 */
	@PutMapping(value = "/storagecontainers")
	ResponseEntity<Void> updateStorageContainer(@RequestBody StorageContainerBeanDto storageContainerDto);
}
