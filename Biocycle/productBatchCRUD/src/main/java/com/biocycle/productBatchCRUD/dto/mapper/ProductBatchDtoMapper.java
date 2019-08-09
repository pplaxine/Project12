package com.biocycle.productBatchCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productBatchCRUD.dto.ProductBatchDto;
import com.biocycle.productBatchCRUD.model.ProductBatch;

@Mapper(componentModel = "spring")
public interface ProductBatchDtoMapper {
	
	ProductBatchDto productBatchToProductBatchDto(ProductBatch productBatch);
	ProductBatch productBatchDtoToProductBatch(ProductBatchDto productBatchDto);
}
