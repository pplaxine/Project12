package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.productRequest.ProductRequestBean;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;

@Mapper(componentModel = "spring")
public interface ProductRequestBeanDtoMapper {
	
	ProductRequestBeanDto productRequestBeanToProductRequestBeanDto(ProductRequestBean productRequestBean);
	ProductRequestBean productRequestBeanDtoToProductRequestBean(ProductRequestBeanDto productRequestBeanDto);
}
