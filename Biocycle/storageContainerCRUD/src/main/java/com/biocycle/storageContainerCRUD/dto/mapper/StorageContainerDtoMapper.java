package com.biocycle.storageContainerCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.storageContainerCRUD.dto.StorageContainerDto;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

@Mapper(componentModel = "spring")
public interface StorageContainerDtoMapper {
	StorageContainerDto storageContainerToStorageContainerDto(StorageContainer storageContainer);
	StorageContainer storageContainerDtoToStorageContainer(StorageContainerDto storageContainerDto);
}
