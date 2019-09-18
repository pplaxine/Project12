package com.biocycle.productDispatchService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productDispatchService.bean.OfferBean;
import com.biocycle.productDispatchService.dto.OfferBeanDto;

/**
 * The Interface OfferBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface OfferBeanDtoMapper {
	
	/**
	 * Offer bean to offer bean dto.
	 *
	 * @param offerBean the offer bean
	 * @return the offer bean dto
	 */
	OfferBeanDto offerBeanToOfferBeanDto(OfferBean offerBean);
	
	/**
	 * Offer bean dto to offer bean.
	 *
	 * @param offerBeanDto the offer bean dto
	 * @return the offer bean
	 */
	OfferBean offerBeanDtoToOfferBean(OfferBeanDto offerBeanDto);
}
