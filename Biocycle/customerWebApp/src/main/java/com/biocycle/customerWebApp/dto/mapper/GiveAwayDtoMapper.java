package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.giveAwayService.GiveAwayBean;
import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;



@Mapper(componentModel = "spring")
public interface GiveAwayDtoMapper {
	
	GiveAwayBeanDto giveAwayToGiveAwayDto(GiveAwayBean giveAway);
	GiveAwayBean giveAwayDtoToGiveAway(GiveAwayBeanDto giveAwayDto);
	
}
