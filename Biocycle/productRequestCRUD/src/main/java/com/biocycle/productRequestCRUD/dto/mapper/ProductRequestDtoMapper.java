package com.biocycle.productRequestCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productRequestCRUD.dto.ProductRequestDto;
import com.biocycle.productRequestCRUD.model.ProductRequest;

/**
 * The Interface ProductRequestDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProductRequestDtoMapper {
	
	/**
	 * Product request to product request dto.
	 *
	 * @param productRequest the product request
	 * @return the product request dto
	 */
	ProductRequestDto productRequestToProductRequestDto(ProductRequest productRequest);
	
	/**
	 * Product request dto to product request.
	 *
	 * @param productRequestDto the product request dto
	 * @return the product request
	 */
	ProductRequest productRequestDtoToProductRequest(ProductRequestDto productRequestDto);
}
