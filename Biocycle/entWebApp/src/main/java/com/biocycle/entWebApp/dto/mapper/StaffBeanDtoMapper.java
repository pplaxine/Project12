package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.StaffBean;
import com.biocycle.entWebApp.dto.StaffBeanDto;

@Mapper(componentModel = "spring")
public interface StaffBeanDtoMapper {
	
	StaffBeanDto staffBeanToStaffBeanDto(StaffBean staffBean);
	StaffBean staffBeanDtoToStaffBean(StaffBeanDto staffBeanDto);
}
