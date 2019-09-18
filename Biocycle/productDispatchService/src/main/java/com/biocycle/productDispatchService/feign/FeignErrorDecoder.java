package com.biocycle.productDispatchService.feign;

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
		
		//RedistributionCRUDMSProxy
		if(methodKey.equals("RedistributionCRUDMSProxy#getRedistributionById(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "RedistributionCRUDMSProxy#getRedistributionById(int) replied : Not found.");
		}
		
		//ProductBatchCRUDMSProxy
		if(methodKey.equals("ProductBatchCRUDMSProxy#updateProductBatchIsAwaitingToBeCollectedStatus(int)")) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "RedistributionCRUDMSProxy#getRedistributionById(int) replied with error.");
		}
		
		
		if(methodKey.equals("ProductRequestCRUDMSProxy#addProductRequestList(List)") && response.status() == HttpStatus.I_AM_A_TEAPOT.value()) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ContraintViolationException in :" + response.request().url());
		}
		return new Exception(response.reason()); 
	}
	
}
