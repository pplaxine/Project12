package com.biocycle.productDispatchService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productDispatchService.bean.ProductRequestBean;
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;

@Mapper(componentModel = "spring")
public interface ProductRequestBeanDtoMapper {
	
	ProductRequestBeanDto productRequestBeanToProductRequestBeanDto(ProductRequestBean productRequestBean);
	ProductRequestBean productRequestBeanDtoToProductRequestBean(ProductRequestBeanDto productRequestBeanDto);
}
