package com.biocycle.giveAwayService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;
import com.biocycle.giveAwayService.dto.proxy.GiveAwayCRUDMSProxy;
import com.biocycle.giveAwayService.exception.GiveAwayNotFoundException;

@Service
public class GiveAwayManager {
	
	@Autowired
	private GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	
	public ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAway(){
		
		ResponseEntity<List<GiveAwayBeanDto>> resp = giveAwayCRUDMSProxy.getAllActiveGiveAway();
		
		if(resp.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new GiveAwayNotFoundException("No active giveAway could be found.");
		}else if(resp.getStatusCode() != HttpStatus.OK){
			return ResponseEntity.status(resp.getStatusCode()).build();
		}
		return resp;
	}
}
