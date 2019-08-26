package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.offer.OfferBean;
import com.biocycle.customerWebApp.dto.OfferBeanDto;


@Mapper(componentModel = "spring")
public interface OfferBeanDtoMapper {
	
	OfferBeanDto offerToOfferDto(OfferBean offer);
	OfferBean offerDtoToOffer(OfferBeanDto offerDto);
}
