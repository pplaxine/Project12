package com.biocycle.offerCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class OfferNotFoundException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException{
	
	/**
	 * Instantiates a new offer not found exception.
	 *
	 * @param message the message
	 */
	public OfferNotFoundException(String message) {
		super(message);
	}
}
