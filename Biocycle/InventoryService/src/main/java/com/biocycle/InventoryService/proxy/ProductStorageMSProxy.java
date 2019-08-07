package com.biocycle.InventoryService.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "productStorageService", url = "localhost:9505")
public interface ProductStorageMSProxy {

}
