package com.biocycle.organisationCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class ConstrainTeaPotException.
 */
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class ConstrainTeaPotException extends RuntimeException{
	
	/**
	 * Instantiates a new constrain tea pot exception.
	 *
	 * @param message the message
	 */
	public ConstrainTeaPotException(String message) {
		super(message);
	}
}
