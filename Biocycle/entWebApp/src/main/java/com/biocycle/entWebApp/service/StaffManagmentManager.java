package com.biocycle.entWebApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import com.biocycle.entWebApp.dto.StaffBeanDto;
import com.biocycle.entWebApp.proxy.StaffCRUDMSProxy;

@Service
public class StaffManagmentManager {
	
	@Autowired
	StaffCRUDMSProxy staffCRUDMSProxy;
	
	public String staffList(Model model) {
		
		try {
			List<StaffBeanDto> staffBeanDtoList = staffCRUDMSProxy.findAllStaff().getBody();
			model.addAttribute("staffBeanDtoList", staffBeanDtoList);
			
		} catch (ResponseStatusException rse) {
			
			String error ="No staff could be found.";
			model.addAttribute("error", error);
			System.out.println(rse.getReason());
			
		} catch (Exception e) {
			model.addAttribute("error", "Error occured. Please Try again.");
		}
		return "staffList";
	}
}
