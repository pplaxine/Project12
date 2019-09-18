package com.biocycle.customerWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.view.PasswordCreationViewDto;
import com.biocycle.customerWebApp.service.OrganisationManager;
import com.biocycle.customerWebApp.service.RedistributionManager;

/**
 * The Class CustomerWebAppController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Controller
public class CustomerWebAppController {
	
	/** The organisation manager. */
	@Autowired
	private OrganisationManager organisationManager;
	
	/** The redistribution manager. */
	@Autowired
	private RedistributionManager redistributionManager;
	
	/**
	 * Home.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/")
	public String home(Model model) {
		return redistributionManager.productSoonToBeExpired(model);
	}
	
	/**
	 * Partnership request.
	 *
	 * @param model the model
	 * @return the string
	 */
	//REQUEST PARTNERSHIP 	---------------------------------------------------------------------------------------
	@RequestMapping("/partnership/request")
	public String partnershipRequest(Model model) {
		model.addAttribute("organisationBeanDto", new OrganisationBeanDto());
		return "requestPartnership";
	}
	
	/**
	 * Save request.
	 *
	 * @param organisationBeanDto the organisation bean dto
	 * @param model the model
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/partnership/request/save")
	public String saveRequest(OrganisationBeanDto organisationBeanDto, Model model, RedirectAttributes red) {
		return organisationManager.saveRequest(organisationBeanDto, model, red);
	}
	
	/**
	 * Partnership info.
	 *
	 * @param model the model
	 * @return the string
	 */
	//WHAT IS PARTNERSHIP	---------------------------------------------------------------------------------------
	@RequestMapping("/partnership/whatisit")
	public String partnershipInfo(Model model) {
		return "whatIsPartnership";
	}
	
	/**
	 * Password creation request.
	 *
	 * @param model the model
	 * @return the string
	 */
	//PASSWORD CREATION		---------------------------------------------------------------------------------------
	@RequestMapping("/authentification/password/creation")
	public String passwordCreationRequest(Model model) {
		model.addAttribute("passwordCreationViewDto", new PasswordCreationViewDto());
		return "passwordCreation";
	}
	
	/**
	 * Save password.
	 *
	 * @param passwordCreationViewDto the password creation view dto
	 * @param model the model
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/authentification/password/save")
	public String savePassword(PasswordCreationViewDto passwordCreationViewDto, Model model, RedirectAttributes red) {
		return organisationManager.savePassword(passwordCreationViewDto, model, red);
	}

}
