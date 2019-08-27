package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.productBatch.ProductBatchBean;
import com.biocycle.entWebApp.dto.ProductBatchBeanDto;

@Mapper(componentModel = "spring")
public interface ProductBatchBeanDtoMapper {
	
	ProductBatchBeanDto productBatchBeanToProductBatchBeanDto(ProductBatchBean productBatchBean);
	ProductBatchBean productBatchBeanDtoToProductBatchBean(ProductBatchBeanDto productBatchBeanDto);
}
