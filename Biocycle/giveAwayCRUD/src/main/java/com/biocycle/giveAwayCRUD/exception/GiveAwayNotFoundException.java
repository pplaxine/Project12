package com.biocycle.giveAwayCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The Class GiveAwayNotFoundException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GiveAwayNotFoundException extends RuntimeException {
	
	/**
	 * Instantiates a new give away not found exception.
	 *
	 * @param message the message
	 */
	public GiveAwayNotFoundException(String message) {
		super(message);
	}
}
