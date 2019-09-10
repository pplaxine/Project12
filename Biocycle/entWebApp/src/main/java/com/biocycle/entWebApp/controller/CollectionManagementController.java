package com.biocycle.entWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.service.CollectionManagmentManager;

@Controller
public class CollectionManagementController {

	@Autowired
	private CollectionManagmentManager collectionManagmentManager;
	
	@RequestMapping("/cse/donation/offers")
	public String donationOfferList(Model model) {
		return collectionManagmentManager.donationOfferList(model);
	}
	
	@RequestMapping("/cse/donation/offers/{giveAwayId}/{containerId}/{status}")
	public String updateDonation(@PathVariable int giveAwayId, @PathVariable int containerId, @PathVariable Boolean status, RedirectAttributes red) {
		return collectionManagmentManager.updateDonation(giveAwayId, containerId, status, red);
	}
}
