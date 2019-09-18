package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.offer.OfferBean;
import com.biocycle.entWebApp.dto.OfferBeanDto;

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
