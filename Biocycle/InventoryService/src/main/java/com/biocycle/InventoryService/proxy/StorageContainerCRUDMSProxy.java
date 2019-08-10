package com.biocycle.InventoryService.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.biocycle.InventoryService.dto.StorageContainerBeanDto;

@FeignClient(name = "storageContainerCRUD", url = "localhost:9005")
public interface StorageContainerCRUDMSProxy {
	
	@GetMapping(value = "/storagecontainers/listId")
	Optional<List<StorageContainerBeanDto>> getStorageContainers(@RequestParam("containerIdList") Integer[]containerIdList);
	
	@PutMapping(value = "/storagecontainers/updatestatus")
	ResponseEntity<Void> updateStorageContainerList(@RequestBody List<StorageContainerBeanDto> storageContainerBeanDtoList);
}