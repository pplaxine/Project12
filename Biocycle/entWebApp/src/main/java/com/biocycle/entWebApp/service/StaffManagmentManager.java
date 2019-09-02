package com.biocycle.entWebApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.OrganisationBeanDto;
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
	
	public String staffForm(StaffBeanDto staffDtoBean, Model model) {
		return"staffForm";
	}
	
	public String createStaff(StaffBeanDto staffBeanDto, Model model, RedirectAttributes red) {
		try {
			staffCRUDMSProxy.addStaff(staffBeanDto);
			String info = "New staff successfully created.";
			red.addFlashAttribute("info", info);

		} catch (ResponseStatusException rse) {
			String error ="Could not register your entry. Please try again.";
			model.addAttribute("error", error);
			return "staffForm";
		
		} catch (Exception e) {
			String error ="Could not register your entry. Please try again.";
			model.addAttribute("error", error);
			return "staffForm";
		}
		
		return"redirect:/hr/staff/form";
	}
	
}
