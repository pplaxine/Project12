package com.biocycle.InventoryService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.model.ProductBatch;

/**
 * The Interface ProductBatchBeanMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProductBatchBeanMapper {
	
	/**
	 * Product batch to product batch bean.
	 *
	 * @param productBatch the product batch
	 * @return the product batch bean
	 */
	ProductBatchBean productBatchToProductBatchBean(ProductBatch productBatch);
	
	/**
	 * Product batch bean toproduct batch.
	 *
	 * @param productBatchBean the product batch bean
	 * @return the product batch
	 */
	ProductBatch productBatchBeanToproductBatch(ProductBatchBean productBatchBean);
}
