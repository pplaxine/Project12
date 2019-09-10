package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.giveAway.GiveAwayBean;
import com.biocycle.entWebApp.dto.GiveAwayBeanDto;

@Mapper(componentModel = "spring")
public interface GiveAwayBeanDtoMapper {
	GiveAwayBeanDto GiveAwayBeanToGiveAwayBeanDto(GiveAwayBean giveAwayBean);
	GiveAwayBean giveAwayBeanDtoToGiveAwayBean(GiveAwayBeanDto giveAwayBeanDto);
}
