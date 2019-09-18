package com.biocycle.giveAwayService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;
import com.biocycle.giveAwayService.service.GiveAwayManager;

/**
 * The Class GiveAwayController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@RestController
public class GiveAwayController {

	/** The give away manager. */
	@Autowired
	private GiveAwayManager giveAwayManager;
	
	/**
	 * Gets the all active give away.
	 *
	 * @return the all active give away
	 */
	//---- GET 
	@GetMapping(value = "/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAway(){
		return giveAwayManager.getAllActiveGiveAway();
	}
	
}
