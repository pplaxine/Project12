package com.biocycle.entWebApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.StaffBeanDto;
import com.biocycle.entWebApp.proxy.StaffCRUDMSProxy;

/**
 * The Class StaffManagmentManager.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Service
public class StaffManagmentManager {
	
	/** The staff CRUDMS proxy. */
	@Autowired
	StaffCRUDMSProxy staffCRUDMSProxy;
	
	/**
	 * Staff list.
	 *
	 * @param model the model
	 * @return the string
	 */
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
	
	/**
	 * Staff form.
	 *
	 * @param staffDtoBean the staff dto bean
	 * @param model the model
	 * @return the string
	 */
	public String staffForm(StaffBeanDto staffDtoBean, Model model) {
		return"staffForm";
	}
	
	/**
	 * Creates the staff.
	 *
	 * @param staffBeanDto the staff bean dto
	 * @param model the model
	 * @param red the red
	 * @return the string
	 */
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
