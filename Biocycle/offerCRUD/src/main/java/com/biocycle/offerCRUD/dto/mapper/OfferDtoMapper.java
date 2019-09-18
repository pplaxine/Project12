package com.biocycle.offerCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.offerCRUD.dto.OfferDto;
import com.biocycle.offerCRUD.model.Offer;

/**
 * The Interface OfferDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface OfferDtoMapper {
	
	/**
	 * Offer to offer dto.
	 *
	 * @param offer the offer
	 * @return the offer dto
	 */
	OfferDto offerToOfferDto(Offer offer);
	
	/**
	 * Offer dto to offer.
	 *
	 * @param offerDto the offer dto
	 * @return the offer
	 */
	Offer offerDtoToOffer(OfferDto offerDto);
}
