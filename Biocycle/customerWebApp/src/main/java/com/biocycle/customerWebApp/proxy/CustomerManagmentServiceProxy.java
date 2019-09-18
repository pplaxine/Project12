package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;

/**
 * The Interface CustomerManagmentServiceProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "customermanagmentservice")
@RibbonClient(name = "customerManagmentService")
public interface CustomerManagmentServiceProxy {

	/**
	 * Adds the organisation.
	 *
	 * @param organisationBeanDto the organisation bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/customermanagmentservice/organisations")
	ResponseEntity<Void> addOrganisation(@RequestBody OrganisationBeanDto organisationBeanDto);
	
	/**
	 * Adds the password.
	 *
	 * @param organisationBeanDto the organisation bean dto
	 * @return the response entity
	 */
	@PostMapping(value = "/customermanagmentservice/organisations/password")
	ResponseEntity<Void> addPassword(@RequestBody OrganisationBeanDto organisationBeanDto);
	
	
	
}
