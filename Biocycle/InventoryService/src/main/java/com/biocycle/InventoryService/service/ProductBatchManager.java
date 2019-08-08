package com.biocycle.InventoryService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.InventoryService.proxy.ProductStorageMSProxy;

@Service
public class ProductBatchManager {
	
	@Autowired
	private ProductBatchCRUDMSProxy productBatchMsProxy;
	@Autowired
	private ProductStorageMSProxy productStorageMsProxy;
	
	
	public ResponseEntity<Void> createProductBatch(ProductBatchBean productBatchBean, int numberOfContainer) {
		
		Optional<List<Integer>> containerIdList = productStorageMsProxy.getContainers(numberOfContainer);
		if(!containerIdList.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		productBatchBean.setStorageContainerId(containerIdList.get());

		return productBatchMsProxy.addProductBatch(productBatchBean);
	}
}
