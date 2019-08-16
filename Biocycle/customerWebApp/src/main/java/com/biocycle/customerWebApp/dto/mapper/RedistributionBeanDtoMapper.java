package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.RedistributionBean;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;

@Mapper(componentModel = "spring")
public interface RedistributionBeanDtoMapper {
	
	RedistributionBeanDto redistributionBeanToRedistributionBeanDto(RedistributionBean redistributionBean);
	RedistributionBean redistributionBeanDtoToRedistributionBean(RedistributionBeanDto redistributionBeanDto);
}
