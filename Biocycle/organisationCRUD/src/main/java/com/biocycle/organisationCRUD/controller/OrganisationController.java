package com.biocycle.organisationCRUD.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.biocycle.organisationCRUD.exception.ConstrainTeaPotException;
import com.biocycle.organisationCRUD.exception.OrganisationNotFoundException;
import com.biocycle.organisationCRUD.helper.OrganisationHelper;
import com.biocycle.organisationCRUD.model.Organisation;

// TODO: Auto-generated Javadoc
/**
 * The Class OrganisationController.
 */
@RestController
public class OrganisationController {
	
	//@Autowired
	//private PasswordEncoder bcPasswordEncodeur;
	
	/** The organisation dao. */
	@Autowired
	private OrganisationDao organisationDao;
	
	/** The organisation dto mapper. */
	@Autowired
	private OrganisationDtoMapper organisationDtoMapper;
	
	/**
	 * Find all organisation.
	 *
	 * @return the response entity
	 */
	//---- GET 
	@GetMapping(value = "/organisations")
	public ResponseEntity<List<OrganisationDto>> findAllOrganisation(){
		List<Organisation> organisationList = organisationDao.findAll();
		if(organisationList.isEmpty()) {
			throw new OrganisationNotFoundException("No organisation could be found.");
		}
		
		List<OrganisationDto> organisationDtoList = OrganisationHelper.EntityListToDtoList(organisationList, organisationDtoMapper);
		
		return ResponseEntity.ok(organisationDtoList);
	}
	
	/**
	 * Find organisation by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping(value="/organisations/{id}")
	public ResponseEntity<OrganisationDto> findOrganisationById(@PathVariable int id){
		
		Optional<Organisation> organisation = organisationDao.findById(id);
		
		if(!organisation.isPresent()) {
			throw new OrganisationNotFoundException("Organisation with id: " + id + " does not exist.");
		}
		
		OrganisationDto organisationDto = organisationDtoMapper.organisationToOrganisationDto(organisation.get());
		
		return ResponseEntity.ok(organisationDto);
	}
	
	/**
	 * Find organisation by email.
	 *
	 * @param email the email
	 * @return the response entity
	 */
	@GetMapping(value="/organisations/email/{email}")
	public ResponseEntity<OrganisationDto> findOrganisationByEmail(@PathVariable String email){
		
		Optional<Organisation> organisation = organisationDao.findOrganisationByEmail(email);
		
		if(!organisation.isPresent()) {
			throw new OrganisationNotFoundException("Organisation with email: " + email + " does not exist.");
		}
		
		OrganisationDto organisationDto = organisationDtoMapper.organisationToOrganisationDto(organisation.get());
		
		return ResponseEntity.ok(organisationDto);
	}
	
	/**
	 * Find all organisation by is validated.
	 *
	 * @param isValidated the is validated
	 * @return the response entity
	 */
	@GetMapping(value="/organisations/validated/{isValidated}")
	public ResponseEntity<List<OrganisationDto>> findAllOrganisationByIsValidated(@PathVariable boolean isValidated){
		
		Optional<List<Organisation>> organisationList = organisationDao.findAllOrganisationByIsValidated(isValidated);
		
		if(!organisationList.isPresent()) {
			throw new OrganisationNotFoundException("No validated organisation found.");
		}
		List<OrganisationDto> organisationDtoList = OrganisationHelper.EntityListToDtoList(organisationList.get(), organisationDtoMapper);
		
		
		return ResponseEntity.ok(organisationDtoList);
	}
	
	
	
	
	//---- DELETE 
	
	/**
	 * Delete organisation.
	 *
	 * @param id the id
	 */
	@DeleteMapping(value = "/organisations/{id}")
	public void deleteOrganisation(@PathVariable int id) {
		organisationDao.deleteById(id);
	}
	
	//---- POST
	
	/**
	 * Adds the organisation.
	 *
	 * @param organisationDto the organisation dto
	 * @return the response entity
	 */
	@PostMapping(value = "/organisations")
	public ResponseEntity<Void> addOrganisation(@RequestBody OrganisationDto organisationDto){
		
		Organisation organisation = organisationDtoMapper.organisationDtoToOrganisation(organisationDto);
	
	 	try {
	 		Organisation organi = organisationDao.save(organisation);		
	 	 	URI location = ServletUriComponentsBuilder
 					.fromCurrentRequest()
 					.path("/{id}")
 					.buildAndExpand(organi.getId())
 					.toUri();
 	
	 	 	return ResponseEntity.created(location).build();
	 	 	
	 	} catch (Exception e) {
			if(e.getClass() == DataIntegrityViolationException.class) {
				throw new ConstrainTeaPotException("Contrain violation : " +e.getMessage());
			}else {
				throw e;
			}
		}
	}

	//---- PUT
	
	/**
	 * Update organisation.
	 *
	 * @param organisationDto the organisation dto
	 * @return the response entity
	 */
	@PutMapping(value = "/organisations")
	public ResponseEntity<Void> updateOrganisation(@RequestBody OrganisationDto organisationDto) {
		Organisation organisation = organisationDtoMapper.organisationDtoToOrganisation(organisationDto);
		organisationDao.save(organisation);
		return ResponseEntity.ok().build();
	}
}
