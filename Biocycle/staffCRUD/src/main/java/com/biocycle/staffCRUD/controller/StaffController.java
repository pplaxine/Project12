package com.biocycle.staffCRUD.controller;

import java.net.URI;
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
import com.biocycle.staffCRUD.exception.StaffNotFoundException;
import com.biocycle.staffCRUD.model.Staff;

@RestController
public class StaffController {
	
	@Autowired
	private StaffDao staffDao;
	
	@GetMapping(value = "/staff/{id}")
	public Optional<Staff> findStaffById(@PathVariable int id){
		
		Optional<Staff> staff = staffDao.findById(id);
		
		if(!staff.isPresent()) {
			throw new StaffNotFoundException("staff with id: " + id + " does not exist.");
		}
		
		return staff;
	}
	
	@DeleteMapping(value = "/staff/{id}")
	public void deleteStaff(@PathVariable int id) {
		staffDao.deleteById(id);
	}
	
	@PostMapping(value = "/staff")
	public ResponseEntity<Void> addStaff(@RequestBody Staff staff){
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
	
	@PutMapping(value = "/staff")
	public void updateStaff(@RequestBody Staff staff) {
		staffDao.save(staff);
	}
	
	
	
	
}















