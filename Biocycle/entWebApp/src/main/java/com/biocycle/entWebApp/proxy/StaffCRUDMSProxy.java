package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.StaffBeanDto;

@FeignClient(name = "zuul-server", contextId = "staffcrud")
@RibbonClient(name = "staffCRUD")
public interface StaffCRUDMSProxy {

	@GetMapping(value = "/staffcrud/staff")
	ResponseEntity<List<StaffBeanDto>> findAllStaff();
	
	@GetMapping(value = "/staffcrud/staff/username/{userName}")
	ResponseEntity<StaffBeanDto> findStaffByUserName(@PathVariable("userName") String userName);
	
	@PostMapping(value = "/staffcrud/staff")
	ResponseEntity<Void> addStaff(@RequestBody StaffBeanDto staffDto);
	
	
	
	
}
