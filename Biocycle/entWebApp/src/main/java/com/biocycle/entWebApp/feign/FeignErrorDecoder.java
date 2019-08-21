package com.biocycle.entWebApp.feign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		//StaffCRUDMSProxy
		if(methodKey.equals("StaffCRUDMSProxy#findStaffByUserName(String)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Staff not found.");
		}
		
		return new Exception("Feign Exception : Status " + response.status() + " . Error while accessing " + methodKey); 
	}
	
}
