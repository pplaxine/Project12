package com.biocycle.customerWebApp.feign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		//CustomerManagmentServiceProxy
		if(methodKey.equals("CustomerManagmentServiceProxy#addOrganisation(OrganisationBeanDto)") && response.status() == 418) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "This email address is already registred.");
		}
		
		if(methodKey.equals("CustomerManagmentServiceProxy#addPassword(OrganisationBeanDto)") && response.status() == 409) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "This email address has already a saved password.");
		}
		if(methodKey.equals("CustomerManagmentServiceProxy#addPassword(OrganisationBeanDto)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No account with this email address found.");
		}
		
		//GiveAwayServiceProxy
		if(methodKey.equals("GiveAwayCRUDMSProxy#addGiveAway(GiveAwayBeanDto)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Not Found ...");
		}
		if(methodKey.equals("GiveAwayCRUDMSProxy#addGiveAway(GiveAwayBeanDto)") && response.status() == 418) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "GiveAway already registreded ...");
		}
		
		//OrganisationCRUDMSProxy
		if(methodKey.equals("OrganisationCRUDMSProxy#findOrganisationByEmail(String)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "OrganisationCRUDMSProxy responed : Not Found");
		}
		
		return new Exception(response.reason()); 
	}
	
}
