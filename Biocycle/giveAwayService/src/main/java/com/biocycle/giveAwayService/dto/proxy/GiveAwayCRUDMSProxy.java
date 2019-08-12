package com.biocycle.giveAwayService.dto.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;

@FeignClient(name = "giveAwayCRUD", url = "localhost:9002")
public interface GiveAwayCRUDMSProxy {
	
	@GetMapping(value = "/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAway();
}
