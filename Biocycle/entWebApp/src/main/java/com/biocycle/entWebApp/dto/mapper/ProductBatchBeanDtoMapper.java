package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.productBatch.ProductBatchBean;
import com.biocycle.entWebApp.dto.ProductBatchBeanDto;

/**
 * The Interface ProductBatchBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProductBatchBeanDtoMapper {
	
	/**
	 * Product batch bean to product batch bean dto.
	 *
	 * @param productBatchBean the product batch bean
	 * @return the product batch bean dto
	 */
	ProductBatchBeanDto productBatchBeanToProductBatchBeanDto(ProductBatchBean productBatchBean);
	
	/**
	 * Product batch bean dto to product batch bean.
	 *
	 * @param productBatchBeanDto the product batch bean dto
	 * @return the product batch bean
	 */
	ProductBatchBean productBatchBeanDtoToProductBatchBean(ProductBatchBeanDto productBatchBeanDto);
}
