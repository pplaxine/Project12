package com.biocycle.staffCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.staffCRUD.dto.StaffDto;
import com.biocycle.staffCRUD.model.Staff;

@Mapper(componentModel = "spring")
public interface StaffDtoMapper {
	
	StaffDto staffToStaffDto(Staff staff);
	Staff staffDtoToStaff(StaffDto staffDto);
}
