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
import com.biocycle.organisationCRUD.dto.OrganisationDto;
import com.biocycle.organisationCRUD.dto.mapper.OrganisationDtoMapper;
import com.biocycle.organisationCRUD.exception.OrganisationNotFoundException;
import com.biocycle.organisationCRUD.model.Organisation;

@RestController
public class OrganisationController {

	@Autowired
	private OrganisationDao organisationDao;
	
	@Autowired
	private OrganisationDtoMapper organisationDtoMapper;
	
	@GetMapping(value="/organisations/{id}")
	public OrganisationDto findOrganisationById(@PathVariable int id){
		
		Optional<Organisation> organisation = organisationDao.findById(id);
		
		if(!organisation.isPresent()) {
			throw new OrganisationNotFoundException("Organisation with id: " + id + " does not exist.");
		}
		
		return organisationDtoMapper.organisationToOrganisationDto(organisation.get());
	}
	
	@DeleteMapping(value = "/organisations/{id}")
	public void deleteOrganisation(@PathVariable int id) {
		organisationDao.deleteById(id);
	}
	
	@PostMapping(value = "/organisations")
	public ResponseEntity<Void> addOrganisation(@RequestBody OrganisationDto organisationDto){
		
		Organisation organisation = organisationDtoMapper.organisationDtoToOrganisation(organisationDto);
		
		Organisation organi = organisationDao.save(organisation);		
	 	if(organi == null) {
	 		return ResponseEntity.noContent().build();
	 	}
	 	
	 	URI location = ServletUriComponentsBuilder
	 					.fromCurrentRequest()
	 					.path("/{id}")
	 					.buildAndExpand(organi.getId())
	 					.toUri();
	 	
	 	return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/organisations")
	public void updateOrganisation(@RequestBody OrganisationDto organisationDto) {
		Organisation organisation = organisationDtoMapper.organisationDtoToOrganisation(organisationDto);
		organisationDao.save(organisation);
	}
}
