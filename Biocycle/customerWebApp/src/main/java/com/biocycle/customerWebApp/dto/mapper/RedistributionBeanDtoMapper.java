package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.redistribution.RedistributionBean;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;

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
