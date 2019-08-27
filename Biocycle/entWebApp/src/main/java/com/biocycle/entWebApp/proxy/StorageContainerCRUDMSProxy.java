package com.biocycle.entWebApp.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biocycle.entWebApp.dto.StorageContainerBeanDto;

@FeignClient(name = "zuul-server", contextId = "storageContainercrud")
@RibbonClient(name = "storageContainerCRUD")
public interface StorageContainerCRUDMSProxy {
	
	@GetMapping(value = "/storagecontainercrud/storagecontainers/listId")
	@ResponseBody
	Optional<List<StorageContainerBeanDto>> findStorageContainerFromIdList(@RequestParam("containerIdList") Integer[]storageContainerIdtab);

}
