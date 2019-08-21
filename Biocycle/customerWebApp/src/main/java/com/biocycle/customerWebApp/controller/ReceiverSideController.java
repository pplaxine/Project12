package com.biocycle.customerWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReceiverSideController {
	
	//REQUEST 				---------------------------------------------------------------------------------------
	
	@RequestMapping("/user2/request/create")
	public String redistributionRequest() {
		return "createRedistributionRequest";
	}
	
	// OFFERS 				---------------------------------------------------------------------------------------	
	@RequestMapping("/user2/offers")
	public String offers() {
		return "receiverPersoSpace";
	}

}
