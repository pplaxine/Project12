package com.biocycle.giveAwayCRUD.controller;

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

import com.biocycle.giveAwayCRUD.dao.GiveAwayDao;
import com.biocycle.giveAwayCRUD.exception.GiveAwayNotFoundException;
import com.biocycle.giveAwayCRUD.model.GiveAway;

@RestController
public class GiveAwayController {
	
	@Autowired
	GiveAwayDao giveAwayDao;
	
	
	@GetMapping(value = "/giveaways/{id}")
	public Optional<GiveAway> findGiveAway(@PathVariable int id){
		Optional<GiveAway> giveAway = giveAwayDao.findById(id);
		
		if(!giveAway.isPresent()) {
			throw new GiveAwayNotFoundException("GiveAway with id: " + id + " doesn't exist.");
		}
		return giveAway;
	}
	
	@DeleteMapping(value = "/giveaways/{id}")
	public void deleteGiveAway(@PathVariable int id) {
		giveAwayDao.deleteById(id);
	}
	
	@PostMapping(value = "/giveaways")
	public ResponseEntity<Void> addGiveAway(@RequestBody GiveAway giveAway){
		
		GiveAway ga = giveAwayDao.save(giveAway);
		
		if(ga == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(ga.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/giveaways")
	public void updateGiveAway(@RequestBody GiveAway giveAway) {
		giveAwayDao.save(giveAway);
	}

}
