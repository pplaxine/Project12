package com.biocycle.collectionManagmentService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.collectionManagmentService.bean.GiveAwayBean;
import com.biocycle.collectionManagmentService.dto.GiveAwayBeanDto;

@Mapper(componentModel = "spring")
public interface GiveAwayBeanDtoMapper {
	GiveAwayBeanDto giveAwayBeanToGiveAwayBeanDto(GiveAwayBean giveAwayBean);
	GiveAwayBean giveAwayBeanDtoToGiveawayBean(GiveAwayBeanDto giveAwayBeanDto);
}
