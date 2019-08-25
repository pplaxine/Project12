package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.giveAway.GiveAwayBean;
import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;

@Mapper(componentModel = "spring")
public interface GiveAwayBeanDtoMapper {
	
	GiveAwayBeanDto giveAwayBeanToGiveAwayBeanDto(GiveAwayBean giveAwayBean);
	GiveAwayBean giveAwayBeanDtoToGiveAwayBean(GiveAwayBeanDto giveAwayBeanDto);
	
}
