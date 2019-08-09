package com.biocycle.offerCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.offerCRUD.dto.OfferDto;
import com.biocycle.offerCRUD.model.Offer;

@Mapper(componentModel = "spring")
public interface OfferDtoMapper {
	
	OfferDto offerToOfferDto(Offer offer);
	Offer offerDtoToOffer(OfferDto offerDto);
}
