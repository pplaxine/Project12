package com.biocycle.InventoryService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.InventoryService.bean.StorageContainerBean;
import com.biocycle.InventoryService.dto.StorageContainerBeanDto;

@Mapper(componentModel = "spring")
public interface StorageContainerDtoMapper {
	
	StorageContainerBeanDto storageContainerBeanToStorageContainerBeanDto(StorageContainerBean storageContainerBean);
	StorageContainerBean storageContainerBeanDtoToStorageContainerBean(StorageContainerBean storageContainerBean);
}
