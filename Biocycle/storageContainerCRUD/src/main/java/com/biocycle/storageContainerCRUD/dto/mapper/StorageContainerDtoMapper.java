package com.biocycle.storageContainerCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.storageContainerCRUD.dto.StorageContainerDto;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

/**
 * The Interface StorageContainerDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface StorageContainerDtoMapper {
	
	/**
	 * Storage container to storage container dto.
	 *
	 * @param storageContainer the storage container
	 * @return the storage container dto
	 */
	StorageContainerDto storageContainerToStorageContainerDto(StorageContainer storageContainer);
	
	/**
	 * Storage container dto to storage container.
	 *
	 * @param storageContainerDto the storage container dto
	 * @return the storage container
	 */
	StorageContainer storageContainerDtoToStorageContainer(StorageContainerDto storageContainerDto);
}
