package com.biocycle.entWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.service.CustomerManagmentManager;

@Controller
public class CustomerManagmentController {
	
	@Autowired
	private CustomerManagmentManager customerManagmentManager;
	
	
	@RequestMapping("/cme/partnerships/requests")
	public String partnershipRequests(Model model, HttpSession session) {
		return customerManagmentManager.partnershipRequests(model, session);
	}
	
	@RequestMapping("/cme/partnerships/requests/validate/{organisationId}")
	public String validatePartnership(@PathVariable int organisationId, RedirectAttributes red) {
		return customerManagmentManager.validatePartnership(organisationId, red);
	}
	
	
}
