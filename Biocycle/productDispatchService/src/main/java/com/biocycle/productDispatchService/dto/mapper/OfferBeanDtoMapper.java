package com.biocycle.productDispatchService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productDispatchService.bean.OfferBean;
import com.biocycle.productDispatchService.dto.OfferBeanDto;

@Mapper(componentModel = "spring")
public interface OfferBeanDtoMapper {
	OfferBeanDto offerBeanToOfferBeanDto(OfferBean offerBean);
	OfferBean offerBeanDtoToOfferBean(OfferBeanDto offerBeanDto);
}
