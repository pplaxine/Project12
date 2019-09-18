package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.offer.OfferBean;
import com.biocycle.customerWebApp.dto.OfferBeanDto;


/**
 * The Interface OfferBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface OfferBeanDtoMapper {
	
	/**
	 * Offer to offer dto.
	 *
	 * @param offer the offer
	 * @return the offer bean dto
	 */
	OfferBeanDto offerToOfferDto(OfferBean offer);
	
	/**
	 * Offer dto to offer.
	 *
	 * @param offerDto the offer dto
	 * @return the offer bean
	 */
	OfferBean offerDtoToOffer(OfferBeanDto offerDto);
}
