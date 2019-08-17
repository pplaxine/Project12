package com.biocycle.customerManagmentService.feign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		if(methodKey.equals("OrganisationCRUDMSProxy#findAllOrganisation()") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No organisation could be found.");
		}
		
		if(methodKey.equals("OrganisationCRUDMSProxy#addOrganisation(OrganisationBeanDto)") && response.status() == HttpStatus.I_AM_A_TEAPOT.value()) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ContraintViolationException in :" + response.request().url());
		}
		return new Exception(response.reason()); 
	}
	
}
