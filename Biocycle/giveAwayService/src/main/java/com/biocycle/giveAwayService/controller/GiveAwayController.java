package com.biocycle.giveAwayService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;
import com.biocycle.giveAwayService.service.GiveAwayManager;

@RestController
public class GiveAwayController {

	@Autowired
	private GiveAwayManager giveAwayManager;
	
	//---- GET 
	@GetMapping(value = "/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAway(){
		return giveAwayManager.getAllActiveGiveAway();
	}
	
}
