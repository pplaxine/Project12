package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.staff.StaffBean;
import com.biocycle.entWebApp.dto.StaffBeanDto;

/**
 * The Interface StaffBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface StaffBeanDtoMapper {
	
	/**
	 * Staff bean to staff bean dto.
	 *
	 * @param staffBean the staff bean
	 * @return the staff bean dto
	 */
	StaffBeanDto staffBeanToStaffBeanDto(StaffBean staffBean);
	
	/**
	 * Staff bean dto to staff bean.
	 *
	 * @param staffBeanDto the staff bean dto
	 * @return the staff bean
	 */
	StaffBean staffBeanDtoToStaffBean(StaffBeanDto staffBeanDto);
}
