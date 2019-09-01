package com.biocycle.entWebApp.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biocycle.entWebApp.dto.StorageContainerBeanDto;

@FeignClient(name = "zuul-server", contextId = "storageContainercrud")
@RibbonClient(name = "storageContainerCRUD")
public interface StorageContainerCRUDMSProxy {
	
	@GetMapping(value = "/storagecontainercrud/storagecontainers/listId")
	@ResponseBody
	Optional<List<StorageContainerBeanDto>> findStorageContainerFromIdList(@RequestParam("containerIdList") Integer[]storageContainerIdtab);
	
	@PutMapping(value = "/storagecontainercrud/storagecontainers/updatestatus")
	ResponseEntity<Void> updateStorageContainer(@RequestBody List<StorageContainerBeanDto> storageContainerDtoList);

}
