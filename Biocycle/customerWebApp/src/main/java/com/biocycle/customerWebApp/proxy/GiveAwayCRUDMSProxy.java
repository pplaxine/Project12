package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;

@FeignClient(name = "zuul-server", contextId = "giveawaycrud")
@RibbonClient(name = "giveAwayCRUD")
public interface GiveAwayCRUDMSProxy {
	
	@PostMapping(value = "/giveawaycrud/giveaways")
	ResponseEntity<Void> addGiveAway(@RequestBody GiveAwayBeanDto giveAwayBeanDto);

}
