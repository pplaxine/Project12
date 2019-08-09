package com.biocycle.collectionRunCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.collectionRunCRUD.dto.CollectionRunDto;
import com.biocycle.collectionRunCRUD.model.CollectionRun;

@Mapper(componentModel = "spring")
public interface CollectionRunDtoMapper {
	CollectionRunDto collectionRunToCollectionRunDto(CollectionRun collectionRun);
	CollectionRun collectionRunDtoToCollectionRun(CollectionRunDto collectionRunDto); 
}
