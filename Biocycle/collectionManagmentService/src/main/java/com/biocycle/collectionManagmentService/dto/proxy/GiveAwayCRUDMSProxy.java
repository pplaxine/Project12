package com.biocycle.collectionManagmentService.dto.proxy;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.biocycle.collectionManagmentService.dto.GiveAwayBeanDto;

@FeignClient(name = "giveAwayCRUD", url = "localhost:9002")
public interface GiveAwayCRUDMSProxy {
	
	@GetMapping(value = "/giveaways/date/{date}")
	ResponseEntity<List<GiveAwayBeanDto>> getAllActiveGiveAwayByDate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd")Date date);
}
