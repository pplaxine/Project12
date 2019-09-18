package com.biocycle.staffCRUD.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biocycle.staffCRUD.dao.StaffDao;
import com.biocycle.staffCRUD.dto.StaffDto;
import com.biocycle.staffCRUD.dto.mapper.StaffDtoMapper;
import com.biocycle.staffCRUD.exception.StaffNotFoundException;
import com.biocycle.staffCRUD.helper.StaffCRUDHelper;
import com.biocycle.staffCRUD.model.Staff;

/**
 * The Class StaffController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@RestController
public class StaffController {
	
	/** The staff dao. */
	@Autowired
	private StaffDao staffDao;
	
	/** The staff dto mapper. */
	@Autowired
	private StaffDtoMapper staffDtoMapper;
	
	/**
	 * Find all staff.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/staff")
	public ResponseEntity<List<StaffDto>> findAllStaff(){
		List<Staff> staffList = staffDao.findAll();
		if(staffList.isEmpty()) {
			throw new StaffNotFoundException("No Staff could be found");
		}
		
		List<StaffDto> staffDtoList = StaffCRUDHelper.EntityListToDtoList(staffList, staffDtoMapper);
		
		return ResponseEntity.ok(staffDtoList);
	}
	
	/**
	 * Find staff by id.
	 *
	 * @param id the id
	 * @return the staff dto
	 */
	@GetMapping(value = "/staff/{id}")
	public StaffDto findStaffById(@PathVariable int id){
		
		Optional<Staff> staff = staffDao.findById(id);
		
		if(!staff.isPresent()) {
			throw new StaffNotFoundException("staff with id: " + id + " does not exist.");
		}
		
		return staffDtoMapper.staffToStaffDto(staff.get());
	}
	
	/**
	 * Find staff by user name.
	 *
	 * @param userName the user name
	 * @return the response entity
	 */
	@GetMapping(value = "/staff/username/{userName}")
	public ResponseEntity<StaffDto> findStaffByUserName(@PathVariable String userName){
		
		Optional<Staff> staff = staffDao.findStaffByUserName(userName);
		
		if(!staff.isPresent()) {
			throw new StaffNotFoundException("staff with id: " + userName + " does not exist.");
		}
		
		StaffDto staffDto = staffDtoMapper.staffToStaffDto(staff.get());
		
		return ResponseEntity.ok(staffDto);
	}
	
	/**
	 * Delete staff.
	 *
	 * @param id the id
	 */
	@DeleteMapping(value = "/staff/{id}")
	public void deleteStaff(@PathVariable int id) {
		staffDao.deleteById(id);
	}
	
	/**
	 * Adds the staff.
	 *
	 * @param staffDto the staff dto
	 * @return the response entity
	 */
	@PostMapping(value = "/staff")
	public ResponseEntity<Void> addStaff(@RequestBody StaffDto staffDto){
		
		Staff staff = staffDtoMapper.staffDtoToStaff(staffDto);
		
		Staff sta = staffDao.save(staff);
		
		if(sta == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(sta.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Update staff.
	 *
	 * @param staffDto the staff dto
	 */
	@PutMapping(value = "/staff")
	public void updateStaff(@RequestBody StaffDto staffDto) {
		Staff staff = staffDtoMapper.staffDtoToStaff(staffDto);
		staffDao.save(staff);
	}
	
	
	
	
}















