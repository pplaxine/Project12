package com.biocycle.giveAwayService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;
import com.biocycle.giveAwayService.dto.proxy.GiveAwayCRUDMSProxy;
import com.biocycle.giveAwayService.exception.GiveAwayNotFoundException;

@RestController
public class GiveAwayController {
	
	@Autowired
	private GiveAwayCRUDMSProxy giveAwayCRUDProxy;
	
	@GetMapping(value = "/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAway(){
		ResponseEntity<List<GiveAwayBeanDto>> resp = giveAwayCRUDProxy.getAllActiveGiveAway();
	
		if(resp.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new GiveAwayNotFoundException("No active giveAway could be found.");
		}else if(resp.getStatusCode() != HttpStatus.OK){
			return ResponseEntity.status(resp.getStatusCode()).build();
		}
		return resp;
	}
}
