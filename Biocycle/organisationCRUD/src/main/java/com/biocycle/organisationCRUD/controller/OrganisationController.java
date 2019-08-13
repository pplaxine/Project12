package com.biocycle.organisationCRUD.controller;

import java.net.URI;
import java.util.List;
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
import com.biocycle.organisationCRUD.helper.OrganisationHelper;
import com.biocycle.organisationCRUD.model.Organisation;

@RestController
public class OrganisationController {

	@Autowired
	private OrganisationDao organisationDao;
	
	@Autowired
	private OrganisationDtoMapper organisationDtoMapper;
	
	//---- GET 
	@GetMapping(value = "/organisations")
	public ResponseEntity<List<OrganisationDto>> findAllOrganisation(){
		List<Organisation> organisationList = organisationDao.findAll();
		if(!organisationList.isEmpty()) {
			throw new OrganisationNotFoundException("No organisation could be found.");
		}
		
		List<OrganisationDto> organisationDtoList = OrganisationHelper.EntityListToDtoList(organisationList, organisationDtoMapper);
		
		return ResponseEntity.ok(organisationDtoList);
	}
	
	@GetMapping(value="/organisations/{id}")
	public ResponseEntity<OrganisationDto> findOrganisationById(@PathVariable int id){
		
		Optional<Organisation> organisation = organisationDao.findById(id);
		
		if(!organisation.isPresent()) {
			throw new OrganisationNotFoundException("Organisation with id: " + id + " does not exist.");
		}
		
		OrganisationDto organisationDto = organisationDtoMapper.organisationToOrganisationDto(organisation.get());
		
		return ResponseEntity.ok(organisationDto);
	}
	
	//---- DELETE 
	
	@DeleteMapping(value = "/organisations/{id}")
	public void deleteOrganisation(@PathVariable int id) {
		organisationDao.deleteById(id);
	}
	
	//---- POST
	
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
	
	//---- PUT
	
	@PutMapping(value = "/organisations")
	public void updateOrganisation(@RequestBody OrganisationDto organisationDto) {
		Organisation organisation = organisationDtoMapper.organisationDtoToOrganisation(organisationDto);
		organisationDao.save(organisation);
	}
}
