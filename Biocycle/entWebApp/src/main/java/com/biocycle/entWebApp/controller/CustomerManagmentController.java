package com.biocycle.entWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.service.CustomerManagmentManager;

/**
 * The Class CustomerManagmentController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Controller
public class CustomerManagmentController {
	
	/** The customer managment manager. */
	@Autowired
	private CustomerManagmentManager customerManagmentManager;
	
	
	/**
	 * Partnership requests.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/cme/partnerships/requests")
	public String partnershipRequests(Model model) {
		return customerManagmentManager.partnershipRequests(model);
	}
	
	/**
	 * Validate partnership.
	 *
	 * @param organisationId the organisation id
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/cme/partnerships/requests/validate/{organisationId}")
	public String validatePartnership(@PathVariable int organisationId, RedirectAttributes red) {
		return customerManagmentManager.validatePartnership(organisationId, red);
	}
	
	
}
