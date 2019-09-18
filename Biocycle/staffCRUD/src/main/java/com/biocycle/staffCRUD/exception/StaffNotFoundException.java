package com.biocycle.staffCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class StaffNotFoundException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StaffNotFoundException extends RuntimeException{
	
	/**
	 * Instantiates a new staff not found exception.
	 *
	 * @param message the message
	 */
	public StaffNotFoundException(String message) {
		super(message);
	}
}
