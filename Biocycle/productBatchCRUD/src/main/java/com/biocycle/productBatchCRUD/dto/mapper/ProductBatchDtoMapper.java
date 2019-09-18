package com.biocycle.productBatchCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productBatchCRUD.dto.ProductBatchDto;
import com.biocycle.productBatchCRUD.model.ProductBatch;

/**
 * The Interface ProductBatchDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProductBatchDtoMapper {
	
	/**
	 * Product batch to product batch dto.
	 *
	 * @param productBatch the product batch
	 * @return the product batch dto
	 */
	ProductBatchDto productBatchToProductBatchDto(ProductBatch productBatch);
	
	/**
	 * Product batch dto to product batch.
	 *
	 * @param productBatchDto the product batch dto
	 * @return the product batch
	 */
	ProductBatch productBatchDtoToProductBatch(ProductBatchDto productBatchDto);
}
