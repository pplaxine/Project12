package com.biocycle.redistributionCRUD.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biocycle.redistributionCRUD.dao.RedistributionDao;
import com.biocycle.redistributionCRUD.exception.RedistributionNotFoundException;
import com.biocycle.redistributionCRUD.model.Redistribution;

@RestController
public class RedistributionController {

	@Autowired
	private RedistributionDao redistributionDao; 
	
	@GetMapping(value = "/redistributions/{id}")
	public Optional<Redistribution> redistribution (@PathVariable int id){
		
		Optional<Redistribution> redistribution = redistributionDao.findById(id);
		
		if(!redistribution.isPresent()) {
			throw new RedistributionNotFoundException("redistribution with id: " + id + " does not exist.");
		}
		
		return redistribution;
	}
	
	@DeleteMapping(value = "/redistributions/{id}")
	public void deleteRedistribution (@PathVariable int id) {
		redistributionDao.deleteById(id);
	}
	
	@PostMapping(value = "/redistributions")
	public ResponseEntity<Void> addRedistribution(@RequestBody Redistribution redistribution){
		
		Redistribution red = redistributionDao.save(redistribution);
		
		if(red == null) {
			ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(red.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/redistributions")
	public void updateRedistribution(@RequestBody Redistribution redistribution) {
		redistributionDao.save(redistribution);
	}
	
	
}
