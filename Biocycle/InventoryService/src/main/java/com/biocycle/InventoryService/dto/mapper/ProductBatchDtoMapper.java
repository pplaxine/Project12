package com.biocycle.InventoryService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.dto.ProductBatchBeanDto;
import com.biocycle.InventoryService.dto.ProductBatchDto;
import com.biocycle.InventoryService.model.ProductBatch;

@Mapper(componentModel = "spring")
public interface ProductBatchDtoMapper {
	
	ProductBatchDto productBatchToProductBatchDto(ProductBatch productBatch);
	ProductBatch productBatchDtoToProductBatch(ProductBatchDto productBatchDto);
	
	ProductBatchBeanDto productBatchBeanToProductBatchBeanDto(ProductBatchBean productBatchBean);
	ProductBatchBean productBatchBeanDtoToProductBatchBean(ProductBatchBeanDto productBatchBeanDto);
}
