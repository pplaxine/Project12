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

@FeignClient(name = "zuul-server", contextId = "giveawaycrud")
@RibbonClient(name = "giveAwayCRUD")
public interface GiveAwayCRUDMSProxy {
	
	@PostMapping(value = "/giveawaycrud/giveaways")
	ResponseEntity<Void> addGiveAway(@RequestBody GiveAwayBeanDto giveAwayBeanDto);
	
	@GetMapping(value = "/giveawaycrud/giveaways/all/{organisationId}")
	ResponseEntity<List<GiveAwayBeanDto>> findAllGiveAwayByOrganisationId(@PathVariable("organisationId") int organisationId);
	
	@DeleteMapping(value = "/giveawaycrud/giveaways/{giveAwayId}")
	public void deleteGiveAway(@PathVariable("giveAwayId") int giveAwayId);
}
