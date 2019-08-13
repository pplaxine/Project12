package com.biocycle.collectionManagmentService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.collectionManagmentService.bean.CollectionRunBean;
import com.biocycle.collectionManagmentService.dto.CollectionRunBeanDto;

@Mapper(componentModel = "spring")
public interface CollectionRunBeanDtoMapper {

	CollectionRunBeanDto collectionRunBeanToCollectionRunBeanDto(CollectionRunBean collectionBeanRun);
	CollectionRunBean collectionRunBeanDtoToCollectionRunBean(CollectionRunBeanDto collectionRunBeanDto);
	
}
