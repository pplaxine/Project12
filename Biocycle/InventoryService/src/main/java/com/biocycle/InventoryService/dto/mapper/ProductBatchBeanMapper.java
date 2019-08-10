package com.biocycle.InventoryService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.model.ProductBatch;

@Mapper(componentModel = "spring")
public interface ProductBatchBeanMapper {
	
	ProductBatchBean productBatchToProductBatchBean(ProductBatch productBatch);
	ProductBatch productBatchBeanToproductBatch(ProductBatchBean productBatchBean);
}
