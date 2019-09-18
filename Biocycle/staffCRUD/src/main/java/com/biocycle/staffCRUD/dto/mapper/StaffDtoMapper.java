package com.biocycle.staffCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.staffCRUD.dto.StaffDto;
import com.biocycle.staffCRUD.model.Staff;

/**
 * The Interface StaffDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface StaffDtoMapper {
	
	/**
	 * Staff to staff dto.
	 *
	 * @param staff the staff
	 * @return the staff dto
	 */
	StaffDto staffToStaffDto(Staff staff);
	
	/**
	 * Staff dto to staff.
	 *
	 * @param staffDto the staff dto
	 * @return the staff
	 */
	Staff staffDtoToStaff(StaffDto staffDto);
}
