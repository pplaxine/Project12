package com.biocycle.InventoryService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * The Class ProductBatchManager.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Service
public class ProductBatchManager {
	
	/** The product storage MS proxy. */
	@Autowired
	private ProductStorageMSProxy productStorageMSProxy;
	
	/** The product batch CRUDMS proxy. */
	@Autowired
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	/** The storage container CRUDMS proxy. */
	@Autowired
	private StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	
	/** The product batch dto mapper. */
	@Autowired
	private ProductBatchDtoMapper productBatchDtoMapper;
	
	/** The product batch bean mapper. */
	@Autowired
	private ProductBatchBeanMapper productBatchBeanMapper;
	
	/**
	 * Creates the product batch.
	 *
	 * @param productBatchDto the product batch dto
	 * @param numberOfContainer the number of container
	 * @return the response entity
	 */
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
