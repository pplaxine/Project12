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
		
		//OrganisationCRUDMSProxy
		if(methodKey.equals("OrganisationCRUDMSProxy#findOrganisationByEmail(String)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "OrganisationCRUDMSProxy responed : Not Found");
		}

		//GiveAwayServiceProxy
		if(methodKey.equals("GiveAwayCRUDMSProxy#addGiveAway(GiveAwayBeanDto)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Not Found ...");
		}
		if(methodKey.equals("GiveAwayCRUDMSProxy#addGiveAway(GiveAwayBeanDto)") && response.status() == 418) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "GiveAway already registreded ...");
		}
		if(methodKey.equals("GiveAwayCRUDMSProxy#findAllGiveAwayByOrganisationId(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No GiveAway could be found.");
		}

		//ProductDispatchServiceProxy
		if(methodKey.equals("ProductDispatchServiceProxy#addRedistributionForRequest(int,List)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductDispatchServiceProxy responed : Not Found");
		}
		
		//ProductRequestCRUDMSProxy
		if(methodKey.equals("ProductRequestCRUDMSProxy#findProductRequestById(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductRequestCRUDMSProxy responed : Not Found");
		}
		
		//RedistributionCRUDMSProxy
		if(methodKey.equals("RedistributionCRUDMSProxy#getAllRedistributionByOrganisationId(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "RedistributionCRUDMSProxy responed : Not Found");
		}
		
		//OfferCRUDMSProxy
		if(methodKey.equals("OfferCRUDMSProxy#findOfferById(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "RedistributionCRUDMSProxy responed : Not Found");
		}
		
		return new Exception(methodKey +" : " + response.reason()); 
	}
	
}
