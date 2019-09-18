package com.biocycle.entWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.StaffBeanDto;
import com.biocycle.entWebApp.service.StaffManagmentManager;

/**
 * The Class StaffManagmentController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Controller
public class StaffManagmentController {

	/** The staff managment manager. */
	@Autowired
	StaffManagmentManager staffManagmentManager;
	
	/**
	 * Staff list.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/hr/staff")
	public String staffList(Model model) {
		return staffManagmentManager.staffList(model);
	}
	
	/**
	 * Staff form.
	 *
	 * @param staffBeanDto the staff bean dto
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/hr/staff/form")
	public String staffForm(StaffBeanDto staffBeanDto, Model model) {
		return staffManagmentManager.staffForm(staffBeanDto, model);
	}
	
	/**
	 * Creates the staff.
	 *
	 * @param staffBeanDto the staff bean dto
	 * @param model the model
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/hr/staff/form/create")
	public String createStaff(StaffBeanDto staffBeanDto, Model model, RedirectAttributes red) {
		return staffManagmentManager.createStaff(staffBeanDto, model,red);
	}
}
