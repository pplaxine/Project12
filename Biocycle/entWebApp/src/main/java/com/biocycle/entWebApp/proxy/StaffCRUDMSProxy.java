package com.biocycle.entWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.entWebApp.dto.StaffBeanDto;

@FeignClient(name = "zuul-server", contextId = "staffcrud")
@RibbonClient(name = "staffCRUD")
public interface StaffCRUDMSProxy {

	@GetMapping(value = "/staffcrud/staff/username/{userName}")
	ResponseEntity<StaffBeanDto> findStaffByUserName(@PathVariable("userName") String userName);
}
