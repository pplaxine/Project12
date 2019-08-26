package com.biocycle.customerWebApp.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;
import com.biocycle.customerWebApp.service.OrganisationManager;
import com.biocycle.customerWebApp.service.RedistributionManager;

@Controller
public class ReceiverSideController {
	
	@Autowired
	private RedistributionManager redistributionManager;
	@Autowired
	private OrganisationManager organisationManager;
	
	//REQUEST 				---------------------------------------------------------------------------------------
	
	//-- ProductRequest
	@RequestMapping("/user2/redistributions/productrequests/form")
	public String productRequestForm(ProductRequestBeanDto productRequestBeanDto, Model model, HttpSession session, Principal principal) {
		organisationManager.addUserInfoToSession(session, principal);
		model.addAttribute("productRequestBeanDto", new ProductRequestBeanDto());
		return "productRequestForm";
	}
	
	@RequestMapping("/user2/redistributions/productrequests/add")
	public String addProductrequestForm(ProductRequestBeanDto productRequestBeanDto, Model model, RedirectAttributes red, HttpSession session) {
		return redistributionManager.addProductRequest(productRequestBeanDto, model, red, session);
	}
	
	@RequestMapping("/user2/redistributions/productrequests/remove/{ref}")
	public String removeProductrequestForm(@PathVariable String ref, Model model, HttpSession session) {
		return redistributionManager.removeProductRequest(ref, model, session);
	}
	
	//-- RedistributionRequest
	@RequestMapping("/user2/redistributions/add")
	public String addRedistribution(ProductRequestBeanDto productRequestBeanDto, Model model, RedirectAttributes red, HttpSession session) {
		return redistributionManager.addRedistribution(productRequestBeanDto, model, red, session);
	}
	
	
	// OFFERS 				---------------------------------------------------------------------------------------	
	@RequestMapping("/user2/offers")
	public String  offers(Model model, HttpSession session, Principal principal) {
		organisationManager.addUserInfoToSession(session, principal);
		return redistributionManager.offers(model, session, principal);
	}
	@RequestMapping("/user2/offers/accept/{offerId}")
	public String acceptOffer(@PathVariable int offerId, RedirectAttributes red) {
		return redistributionManager.updateOfferStatus(offerId, true, red);
	}
	@RequestMapping("/user2/offers/refuse/{offerId}")
	public String refuseOffer(@PathVariable int offerId, RedirectAttributes red) {
		return redistributionManager.updateOfferStatus(offerId, false, red);
	}

}
