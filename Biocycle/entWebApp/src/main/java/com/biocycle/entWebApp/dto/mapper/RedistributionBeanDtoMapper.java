package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.redistribution.RedistributionBean;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;

@Mapper(componentModel = "spring")
public interface RedistributionBeanDtoMapper {
	
	RedistributionBeanDto redistributionBeanToRedistributionBeanDto(RedistributionBean redistributionBean);
	RedistributionBean redistributionBeanDtoToRedistributionBean(RedistributionBeanDto redistributionBeanDto);
}
