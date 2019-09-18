package com.biocycle.productRequestCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ConstrainTeaPotException.
 * 
 * @author Philippe plaxine
 * @version 1.0
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
