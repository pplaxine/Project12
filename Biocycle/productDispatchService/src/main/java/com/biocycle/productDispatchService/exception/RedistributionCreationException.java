package com.biocycle.productDispatchService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class RedistributionCreationException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class RedistributionCreationException extends RuntimeException {
	
	/**
	 * Instantiates a new redistribution creation exception.
	 *
	 * @param message the message
	 */
	public RedistributionCreationException(String message) {
		super(message);
	}
}
