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


@FeignClient(name = "zuul-server", contextId = "giveawaycrud")
@RibbonClient(name = "giveAwayCRUD")
public interface GiveAwayCRUDMSProxy {
	
	@GetMapping(value = "/giveawaycrud/giveaways/{id}")
	GiveAwayBeanDto findGiveAway(@PathVariable int id);
	
	@GetMapping(value = "/giveawaycrud/giveaways/active")
	ResponseEntity<List<GiveAwayBeanDto>> findActiveGiveAway();
	
	@PutMapping(value = "/giveawaycrud/giveaways")
	void updateGiveAway(@RequestBody GiveAwayBeanDto giveAwayBeanDto);
}
