package com.biocycle.giveAwayService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.giveAwayService.bean.GiveAwayBean;
import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;

/**
 * The Interface GiveAwayBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface GiveAwayBeanDtoMapper {
	
	/**
	 * Give away bean to give away bean dto.
	 *
	 * @param giveAwayBean the give away bean
	 * @return the give away bean dto
	 */
	GiveAwayBeanDto GiveAwayBeanToGiveAwayBeanDto(GiveAwayBean giveAwayBean);
	
	/**
	 * Give away bean dto to give away bean.
	 *
	 * @param giveAwayBeanDto the give away bean dto
	 * @return the give away bean
	 */
	GiveAwayBean giveAwayBeanDtoToGiveAwayBean(GiveAwayBeanDto giveAwayBeanDto);
}
