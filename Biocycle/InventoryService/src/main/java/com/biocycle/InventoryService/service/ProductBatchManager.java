package com.biocycle.InventoryService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.dto.ProductBatchBeanDto;
import com.biocycle.InventoryService.dto.ProductBatchDto;
import com.biocycle.InventoryService.dto.StorageContainerBeanDto;
import com.biocycle.InventoryService.dto.mapper.ProductBatchBeanMapper;
import com.biocycle.InventoryService.dto.mapper.ProductBatchDtoMapper;
import com.biocycle.InventoryService.model.ProductBatch;
import com.biocycle.InventoryService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.InventoryService.proxy.ProductStorageMSProxy;
import com.biocycle.InventoryService.proxy.StorageContainerCRUDMSProxy;

@Service
public class ProductBatchManager {
	
	@Autowired
	private ProductBatchCRUDMSProxy productBatchMsProxy;
	@Autowired
	private ProductStorageMSProxy productStorageMsProxy;
	@Autowired
	private StorageContainerCRUDMSProxy storageContainerMsProxy;
	@Autowired
	private ProductBatchDtoMapper productBatchDtoMapper;
	@Autowired
	private ProductBatchBeanMapper productBatchBeanMapper;
	
	public ResponseEntity<Void> createProductBatch(ProductBatchDto productBatchDto, int numberOfContainer) {
		
		//Dto to model
		ProductBatch productBatch = productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDto);
		
		//Object construction -----
		ResponseEntity<List<Integer>> storageContainerIdList = productStorageMsProxy.getOptimizedSpaceStorageContainers(numberOfContainer);
		if(storageContainerIdList.getStatusCode() != HttpStatus.OK) {
			return ResponseEntity.status(storageContainerIdList.getStatusCode()).build();
		}
		
		productBatch.setStorageContainerId(storageContainerIdList.getBody());
		
		//model to Bean 
		ProductBatchBean productBatchBean = productBatchBeanMapper.productBatchToProductBatchBean(productBatch);
		
		//Bean to Dto 
		ProductBatchBeanDto productBatchBeanDto = productBatchDtoMapper.productBatchBeanToProductBatchBeanDto(productBatchBean);
		
		//ProductBatchCRUD MS call to persist data
		ResponseEntity<Void> productBatchResp = productBatchMsProxy.addProductBatch(productBatchBeanDto);		
		
		//if data not persisted transfert response 
		if(productBatchResp.getStatusCode() != HttpStatus.CREATED) {
			return productBatchResp;
		}
		
		//if data persisted update containers Status
		Integer[]containerIdList = storageContainerIdList.getBody().stream().toArray(Integer[]::new);	//list to array 
		Optional<List<StorageContainerBeanDto>> storageContainerBeanDtoList = storageContainerMsProxy.getStorageContainers(containerIdList);
		if(!storageContainerBeanDtoList.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		storageContainerBeanDtoList.get().forEach(e -> e.setIsAvailable(false));
		
		ResponseEntity<Void> storageContainerResp = storageContainerMsProxy.updateStorageContainerList(storageContainerBeanDtoList.get());
		if(storageContainerResp.getStatusCode() != HttpStatus.NO_CONTENT) {
			return storageContainerResp;
		}

		return productBatchResp;		//transfered to ProductBatchCRUD MS for persistence 
	}
}
