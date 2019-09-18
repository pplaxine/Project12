package com.biocycle.InventoryService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.InventoryService.bean.StorageContainerBean;
import com.biocycle.InventoryService.dto.StorageContainerBeanDto;

/**
 * The Interface StorageContainerDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface StorageContainerDtoMapper {
	
	/**
	 * Storage container bean to storage container bean dto.
	 *
	 * @param storageContainerBean the storage container bean
	 * @return the storage container bean dto
	 */
	StorageContainerBeanDto storageContainerBeanToStorageContainerBeanDto(StorageContainerBean storageContainerBean);
	
	/**
	 * Storage container bean dto to storage container bean.
	 *
	 * @param storageContainerBean the storage container bean
	 * @return the storage container bean
	 */
	StorageContainerBean storageContainerBeanDtoToStorageContainerBean(StorageContainerBean storageContainerBean);
}
