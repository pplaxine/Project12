package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.storageContainer.StorageContainerBean;
import com.biocycle.entWebApp.dto.StorageContainerBeanDto;

@Mapper(componentModel = "spring")
public interface StorageContainerBeanDtoMapper {
	StorageContainerBeanDto storageContainerBeanToStorageContainerBeanDto(StorageContainerBean storageContainerBean);
	StorageContainerBean storageContainerBeanDtoToStorageContainerBean(StorageContainerBeanDto storageContainerBeanDto);
}
