package com.biocycle.giveAwayService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biocycle.giveAwayService.bean.GiveAwayBean;
import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;
import com.biocycle.giveAwayService.dto.mapper.GiveAwayBeanDtoMapper;
import com.biocycle.giveAwayService.dto.proxy.GiveAwayCRUDMSProxy;

@Service
public class GiveAwayManager {

	@Autowired
	private GiveAwayBeanDtoMapper giveAwayBeanDtoMapper;
	
	@Autowired
	private GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	

}
