package com.biocycle.productDispatchService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.productDispatchService.bean.RedistributionBean;
import com.biocycle.productDispatchService.dto.RedistributionBeanDto;

@Mapper(componentModel = "spring")
public interface RedistributionBeanDtoMapper {
	
	RedistributionBeanDto redistributionBeanToRedistributionBeanDto(RedistributionBean redistributionBean);
	RedistributionBean redistributionBeanDtoToRedistributionBean(RedistributionBeanDto redistributionBeanDto);
}
