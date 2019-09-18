package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.redistribution.RedistributionBean;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;

/**
 * The Interface RedistributionBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface RedistributionBeanDtoMapper {
	
	/**
	 * Redistribution bean to redistribution bean dto.
	 *
	 * @param redistributionBean the redistribution bean
	 * @return the redistribution bean dto
	 */
	RedistributionBeanDto redistributionBeanToRedistributionBeanDto(RedistributionBean redistributionBean);
	
	/**
	 * Redistribution bean dto to redistribution bean.
	 *
	 * @param redistributionBeanDto the redistribution bean dto
	 * @return the redistribution bean
	 */
	RedistributionBean redistributionBeanDtoToRedistributionBean(RedistributionBeanDto redistributionBeanDto);
}
