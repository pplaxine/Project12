package com.biocycle.organisationCRUD.controller;

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

import com.biocycle.organisationCRUD.dao.OrganisationDao;
import com.biocycle.organisationCRUD.exception.OrganisationNotFoundException;
import com.biocycle.organisationCRUD.model.Organisation;
import com.biocycle.organisationCRUD.model.Organisation;

@RestController
public class OrganisationController {

	@Autowired
	private OrganisationDao organisationDao;
	
	@GetMapping(value="/organisations/{id}")
	public Optional<Organisation> findOrganisationById(@PathVariable int id){
		
		Optional<Organisation> organisation = organisationDao.findById(id);
		
		if(!organisation.isPresent()) {
			throw new OrganisationNotFoundException("Organisation with id: " + id + " does not exit.");
		}
		
		return organisation;
	}
	
	@DeleteMapping(value = "/organisations/{id}")
	public void deleteOrganisation(@PathVariable int id) {
		organisationDao.deleteById(id);
	}
	
	@PostMapping(value = "/organisations")
	public ResponseEntity<Void> addOrganisation(@RequestBody Organisation organisation){
	 	
		Organisation orga = organisationDao.save(organisation);		//if created send back created object
	 	if(orga == null) {
	 		return ResponseEntity.noContent().build();
	 	}
	 	
	 	URI location = ServletUriComponentsBuilder
	 					.fromCurrentRequest()
	 					.path("/{id}")
	 					.buildAndExpand(orga.getId())
	 					.toUri();
	 	
	 	return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/organisations")
	public void updateOrganisation(@RequestBody Organisation organisation) {
		organisationDao.save(organisation);
	}
}
