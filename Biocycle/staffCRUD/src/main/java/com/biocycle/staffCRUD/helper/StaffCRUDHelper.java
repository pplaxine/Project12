package com.biocycle.staffCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.staffCRUD.dto.StaffDto;
import com.biocycle.staffCRUD.dto.mapper.StaffDtoMapper;
import com.biocycle.staffCRUD.model.Staff;

public class StaffCRUDHelper {
	
	public static List<StaffDto> EntityListToDtoList(List<Staff> staffList, StaffDtoMapper staffDtoMapper){
		List<StaffDto> staffDtoList = new ArrayList<>();

		for (Staff staff : staffList) {
			StaffDto staffDto = staffDtoMapper.staffToStaffDto(staff);
			staffDtoList.add(staffDto);
		}
		return staffDtoList;
	}
}
