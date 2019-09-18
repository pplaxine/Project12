package com.biocycle.redistributionCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class RedistributionNotFoundException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RedistributionNotFoundException extends RuntimeException {
	
	/**
	 * Instantiates a new redistribution not found exception.
	 *
	 * @param message the message
	 */
	public RedistributionNotFoundException(String message) {
		super(message);
	}
}
