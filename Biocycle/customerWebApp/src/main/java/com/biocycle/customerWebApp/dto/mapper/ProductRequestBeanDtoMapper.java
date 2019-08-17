package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.productRequest.ProductRequestBean;
import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;

@Mapper(componentModel = "spring")
public interface ProductRequestBeanDtoMapper {
	
	ProductRequestBeanDto productRequestBeanToProductRequestBeanDto(ProductRequestBean productRequestBean);
	ProductRequestBean productRequestBeanDtoToProductRequestBean(ProductRequestBeanDto productRequestBeanDto);
}
