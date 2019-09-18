package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.storageContainer.StorageContainerBean;
import com.biocycle.entWebApp.dto.StorageContainerBeanDto;

/**
 * The Interface StorageContainerBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface StorageContainerBeanDtoMapper {
	
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
	 * @param storageContainerBeanDto the storage container bean dto
	 * @return the storage container bean
	 */
	StorageContainerBean storageContainerBeanDtoToStorageContainerBean(StorageContainerBeanDto storageContainerBeanDto);
}
