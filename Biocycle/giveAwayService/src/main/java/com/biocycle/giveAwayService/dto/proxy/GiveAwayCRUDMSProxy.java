package com.biocycle.giveAwayService.dto.proxy;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;

@FeignClient(name = "giveAwayCRUD")
@RibbonClient(name = "giveAwayCRUD")
public interface GiveAwayCRUDMSProxy {
	
	@GetMapping(value = "/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAway();
	
	@GetMapping(value = "/giveaways/date/{date}")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAwayByDate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd")Date date);
	
	
}
