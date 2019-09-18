package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.GiveAwayBeanDto;


/**
 * The Interface GiveAwayCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "giveawaycrud")
@RibbonClient(name = "giveAwayCRUD")
public interface GiveAwayCRUDMSProxy {
	
	/**
	 * Find give away.
	 *
	 * @param id the id
	 * @return the give away bean dto
	 */
	@GetMapping(value = "/giveawaycrud/giveaways/{id}")
	GiveAwayBeanDto findGiveAway(@PathVariable int id);
	
	/**
	 * Find active give away.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/giveawaycrud/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> findActiveGiveAway();
	
	/**
	 * Update give away.
	 *
	 * @param giveAwayBeanDto the give away bean dto
	 */
	@PutMapping(value = "/giveawaycrud/giveaways")
	void updateGiveAway(@RequestBody GiveAwayBeanDto giveAwayBeanDto);
}
