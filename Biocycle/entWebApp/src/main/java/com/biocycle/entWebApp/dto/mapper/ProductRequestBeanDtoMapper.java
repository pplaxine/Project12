package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.productRequest.ProductRequestBean;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;

/**
 * The Interface ProductRequestBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface ProductRequestBeanDtoMapper {
	
	/**
	 * Product request bean to product request bean dto.
	 *
	 * @param productRequestBean the product request bean
	 * @return the product request bean dto
	 */
	ProductRequestBeanDto productRequestBeanToProductRequestBeanDto(ProductRequestBean productRequestBean);
	
	/**
	 * Product request bean dto to product request bean.
	 *
	 * @param productRequestBeanDto the product request bean dto
	 * @return the product request bean
	 */
	ProductRequestBean productRequestBeanDtoToProductRequestBean(ProductRequestBeanDto productRequestBeanDto);
}
