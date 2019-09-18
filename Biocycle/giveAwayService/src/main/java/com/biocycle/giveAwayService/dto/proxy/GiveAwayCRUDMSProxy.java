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

/**
 * The Interface GiveAwayCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "giveAwayCRUD")
@RibbonClient(name = "giveAwayCRUD")
public interface GiveAwayCRUDMSProxy {
	
	/**
	 * Gets the all active give away.
	 *
	 * @return the all active give away
	 */
	@GetMapping(value = "/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAway();
	
	/**
	 * Gets the all active give away by date.
	 *
	 * @param date the date
	 * @return the all active give away by date
	 */
	@GetMapping(value = "/giveaways/date/{date}")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAwayByDate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd")Date date);
	
	
}
