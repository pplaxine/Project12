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
import com.biocycle.InventoryService.exception.ProductBatchCreationErrorException;
import com.biocycle.InventoryService.model.ProductBatch;
import com.biocycle.InventoryService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.InventoryService.proxy.ProductStorageMSProxy;
import com.biocycle.InventoryService.proxy.StorageContainerCRUDMSProxy;

@Service
public class ProductBatchManager {
	
	@Autowired
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	@Autowired
	private ProductStorageMSProxy productStorageMSProxy;
	@Autowired
	private StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	@Autowired
	private ProductBatchDtoMapper productBatchDtoMapper;
	@Autowired
	private ProductBatchBeanMapper productBatchBeanMapper;
	
	public ResponseEntity<Void> createProductBatch(ProductBatchDto productBatchDto, int numberOfContainer) {
		
		//Dto to model
		ProductBatch productBatch = productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDto);
		
		//Get empty containers space 
		ResponseEntity<List<Integer>> storageContainerIdList = productStorageMSProxy.getOptimizedSpaceStorageContainers(numberOfContainer);
		productBatch.setStorageContainerId(storageContainerIdList.getBody());
		
		//model to Bean 
		ProductBatchBean productBatchBean = productBatchBeanMapper.productBatchToProductBatchBean(productBatch);
		//Bean to BeanDto 
		ProductBatchBeanDto productBatchBeanDto = productBatchDtoMapper.productBatchBeanToProductBatchBeanDto(productBatchBean);
		
		//Persist productBatch 
		ResponseEntity<Void> productBatchResp = productBatchCRUDMSProxy.addProductBatch(productBatchBeanDto);	

		//Retrieve the StorageContainer been 
		Integer[]containerIdList = storageContainerIdList.getBody().stream().toArray(Integer[]::new);	//list to array 
		Optional<List<StorageContainerBeanDto>> storageContainerBeanDtoList = storageContainerCRUDMSProxy.getStorageContainers(containerIdList);
		//set available to false 
		storageContainerBeanDtoList.get().forEach(e -> e.setIsAvailable(false));
		//persist StorageContainer list  
		storageContainerCRUDMSProxy.updateStorageContainerList(storageContainerBeanDtoList.get());
			
		return productBatchResp;
	}
}
