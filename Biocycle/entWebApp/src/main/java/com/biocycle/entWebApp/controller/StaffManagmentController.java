package com.biocycle.entWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biocycle.entWebApp.service.StaffManagmentManager;

@Controller
public class StaffManagmentController {

	@Autowired
	StaffManagmentManager staffManagmentManager;
	
	@RequestMapping("/hr/staff")
	public String staffList(Model model) {
		return staffManagmentManager.staffList(model);
	}
}
