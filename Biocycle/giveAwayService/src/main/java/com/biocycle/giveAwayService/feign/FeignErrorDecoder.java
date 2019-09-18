package com.biocycle.giveAwayService.feign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * The Class FeignErrorDecoder.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder{

	/**
	 * Decode.
	 *
	 * @param methodKey the method key
	 * @param response the response
	 * @return the exception
	 */
	@Override
	public Exception decode(String methodKey, Response response) {
		
		if(methodKey.equals("GiveAwayCRUDMSProxy#getAllActiveGiveAway()") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "No active GiveAway could be found.");
		}
		return new Exception(response.reason()); 
	}
	
}
