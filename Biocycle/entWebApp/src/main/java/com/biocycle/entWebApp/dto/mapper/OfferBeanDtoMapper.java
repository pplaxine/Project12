package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.offer.OfferBean;
import com.biocycle.entWebApp.dto.OfferBeanDto;

@Mapper(componentModel = "spring")
public interface OfferBeanDtoMapper {
	OfferBeanDto offerBeanToOfferBeanDto(OfferBean offerBean);
	OfferBean offerBeanDtoToOfferBean(OfferBeanDto offerBeanDto);
}
