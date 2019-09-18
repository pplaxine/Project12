package com.biocycle.customerWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;

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
	 * Adds the give away.
	 *
	 * @param giveAwayBeanDto the give away bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/giveawaycrud/giveaways")
	ResponseEntity<Void> addGiveAway(@RequestBody GiveAwayBeanDto giveAwayBeanDto);
	
	/**
	 * Find all give away by organisation id.
	 *
	 * @param organisationId the organisation id
	 * @return the response entity
	 */
	@GetMapping(value = "/giveawaycrud/giveaways/all/{organisationId}")
	ResponseEntity<List<GiveAwayBeanDto>> findAllGiveAwayByOrganisationId(@PathVariable("organisationId") int organisationId);
	
	/**
	 * Delete give away.
	 *
	 * @param giveAwayId the give away id
	 */
	@DeleteMapping(value = "/giveawaycrud/giveaways/{giveAwayId}")
	public void deleteGiveAway(@PathVariable("giveAwayId") int giveAwayId);
}
