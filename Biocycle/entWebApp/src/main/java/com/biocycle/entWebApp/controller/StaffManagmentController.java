package com.biocycle.entWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.dto.StaffBeanDto;
import com.biocycle.entWebApp.service.StaffManagmentManager;

@Controller
public class StaffManagmentController {

	@Autowired
	StaffManagmentManager staffManagmentManager;
	
	@RequestMapping("/hr/staff")
	public String staffList(Model model) {
		return staffManagmentManager.staffList(model);
	}
	
	@RequestMapping("/hr/staff/form")
	public String staffForm(StaffBeanDto staffBeanDto, Model model) {
		return staffManagmentManager.staffForm(staffBeanDto, model);
	}
	
	@RequestMapping("/hr/staff/form/create")
	public String createStaff(StaffBeanDto staffBeanDto, Model model, RedirectAttributes red) {
		return staffManagmentManager.createStaff(staffBeanDto, model,red);
	}
}
