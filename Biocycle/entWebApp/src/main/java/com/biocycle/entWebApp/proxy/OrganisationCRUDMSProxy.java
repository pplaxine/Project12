package com.biocycle.entWebApp.proxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.entWebApp.dto.OrganisationBeanDto;

/**
 * The Interface OrganisationCRUDMSProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "organisationcrud")
@RibbonClient(name = "organisationCRUD")
public interface OrganisationCRUDMSProxy {

	/**
	 * Find all organisation by is validated.
	 *
	 * @param isValidated the is validated
	 * @return the response entity
	 */
	@GetMapping(value = "/organisationcrud/organisations/validated/{isValidated}")
	ResponseEntity<List<OrganisationBeanDto>>  findAllOrganisationByIsValidated(@PathVariable("isValidated") boolean isValidated);
	
	/**
	 * Update organisation.
	 *
	 * @param organisationBeanDto the organisation bean dto
	 * @return the response entity
	 */
	@PutMapping(value = "/organisationcrud/organisations")
	ResponseEntity<Void> updateOrganisation(@RequestBody OrganisationBeanDto organisationBeanDto);
	
	/**
	 * Find organisation by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping(value = "/organisationcrud/organisations/{id}")
	ResponseEntity<OrganisationBeanDto> findOrganisationById(@PathVariable("id") int id);
}
