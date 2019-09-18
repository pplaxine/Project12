package com.biocycle.InventoryService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.dto.ProductBatchBeanDto;
import com.biocycle.InventoryService.dto.ProductBatchDto;
import com.biocycle.InventoryService.model.ProductBatch;

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
