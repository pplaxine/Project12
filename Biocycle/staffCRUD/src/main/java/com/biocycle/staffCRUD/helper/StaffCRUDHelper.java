package com.biocycle.staffCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.staffCRUD.dto.StaffDto;
import com.biocycle.staffCRUD.dto.mapper.StaffDtoMapper;
import com.biocycle.staffCRUD.model.Staff;

/**
 * The Class StaffCRUDHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class StaffCRUDHelper {
	
	/**
	 * Entity list to dto list.
	 *
	 * @param staffList the staff list
	 * @param staffDtoMapper the staff dto mapper
	 * @return the list
	 */
	public static List<StaffDto> EntityListToDtoList(List<Staff> staffList, StaffDtoMapper staffDtoMapper){
		List<StaffDto> staffDtoList = new ArrayList<>();

		for (Staff staff : staffList) {
			StaffDto staffDto = staffDtoMapper.staffToStaffDto(staff);
			staffDtoList.add(staffDto);
		}
		return staffDtoList;
	}
}
