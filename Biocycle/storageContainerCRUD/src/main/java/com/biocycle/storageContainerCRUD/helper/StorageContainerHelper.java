package com.biocycle.storageContainerCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.storageContainerCRUD.dto.StorageContainerDto;
import com.biocycle.storageContainerCRUD.dto.mapper.StorageContainerDtoMapper;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

/**
 * The Class StorageContainerHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class StorageContainerHelper {
	
	/**
	 * Entity list to dto list.
	 *
	 * @param storageContainerList the storage container list
	 * @param storageContainerDtoMapper the storage container dto mapper
	 * @return the list
	 */
	public static List<StorageContainerDto> entityListToDtoList(List<StorageContainer> storageContainerList, StorageContainerDtoMapper storageContainerDtoMapper){
		
		List<StorageContainerDto> storageContainerDtoList = new ArrayList<>();
		
		for (StorageContainer sc : storageContainerList) {
			StorageContainerDto scDto = storageContainerDtoMapper.storageContainerToStorageContainerDto(sc);
			storageContainerDtoList.add(scDto);
		}
		
		return storageContainerDtoList;
	}
	
	/**
	 * Dto list to entity list.
	 *
	 * @param storageContainerDtoList the storage container dto list
	 * @param storageContainerDtoMapper the storage container dto mapper
	 * @return the list
	 */
	public static List<StorageContainer> dtoListToEntityList(List<StorageContainerDto> storageContainerDtoList, StorageContainerDtoMapper storageContainerDtoMapper){
		
		List<StorageContainer> storageContainerList = new ArrayList<>();
		
		for (StorageContainerDto scd : storageContainerDtoList) {
			StorageContainer sc = storageContainerDtoMapper.storageContainerDtoToStorageContainer(scd);
			storageContainerList.add(sc);
		}
		
		return storageContainerList;
	}
	
}
