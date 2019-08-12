package com.biocycle.giveAwayService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.giveAwayService.bean.GiveAwayBean;
import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;

@Mapper(componentModel = "spring")
public interface GiveAwayBeanDtoMapper {
	GiveAwayBeanDto GiveAwayBeanToGiveAwayBeanDto(GiveAwayBean giveAwayBean);
	GiveAwayBean giveAwayBeanDtoToGiveAwayBean(GiveAwayBeanDto giveAwayBeanDto);
}
