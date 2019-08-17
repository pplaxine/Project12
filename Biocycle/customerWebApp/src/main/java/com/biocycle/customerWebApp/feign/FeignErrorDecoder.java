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
		
		if(methodKey.equals("CustomerManagmentServiceProxy#addOrganisation(OrganisationBeanDto)") && response.status() == 418) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "This email address is already registred.");
		}
		return new Exception(response.reason()); 
	}
	
}
