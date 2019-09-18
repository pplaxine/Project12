package com.biocycle.entWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.service.CollectionManagmentManager;

/**
 * The Class CollectionManagementController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Controller
public class CollectionManagementController {

	/** The collection managment manager. */
	@Autowired
	private CollectionManagmentManager collectionManagmentManager;
	
	/**
	 * Donation offer list.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/cse/donation/offers")
	public String donationOfferList(Model model) {
		return collectionManagmentManager.donationOfferList(model);
	}
	
	/**
	 * Update donation.
	 *
	 * @param giveAwayId the give away id
	 * @param containerId the container id
	 * @param status the status
	 * @param red the red
	 * @return the string
	 */
	@RequestMapping("/cse/donation/offers/{giveAwayId}/{containerId}/{status}")
	public String updateDonation(@PathVariable int giveAwayId, @PathVariable int containerId, @PathVariable Boolean status, RedirectAttributes red) {
		return collectionManagmentManager.updateDonation(giveAwayId, containerId, status, red);
	}
}
