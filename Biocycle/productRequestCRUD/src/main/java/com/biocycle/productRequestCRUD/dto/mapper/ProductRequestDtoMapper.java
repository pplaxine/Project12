package com.biocycle.productRequestCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productRequestCRUD.dto.ProductRequestDto;
import com.biocycle.productRequestCRUD.model.ProductRequest;

@Mapper(componentModel = "spring")
public interface ProductRequestDtoMapper {
	
	ProductRequestDto productRequestToProductRequestDto(ProductRequest productRequest);
	ProductRequest productRequestDtoToProductRequest(ProductRequestDto productRequestDto);
}
