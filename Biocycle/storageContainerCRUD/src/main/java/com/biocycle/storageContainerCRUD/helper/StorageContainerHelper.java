package com.biocycle.storageContainerCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.storageContainerCRUD.dto.StorageContainerDto;
import com.biocycle.storageContainerCRUD.dto.mapper.StorageContainerDtoMapper;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

public class StorageContainerHelper {
	
	public static List<StorageContainerDto> entityListToDtoList(List<StorageContainer> storageContainerList, StorageContainerDtoMapper storageContainerDtoMapper){
		
		List<StorageContainerDto> storageContainerDtoList = new ArrayList<>();
		
		for (StorageContainer sc : storageContainerList) {
			StorageContainerDto scDto = storageContainerDtoMapper.storageContainerToStorageContainerDto(sc);
			storageContainerDtoList.add(scDto);
		}
		
		return storageContainerDtoList;
	}
	
}
