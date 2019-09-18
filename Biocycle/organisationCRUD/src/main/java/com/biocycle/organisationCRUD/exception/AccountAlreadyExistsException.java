package com.biocycle.organisationCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountAlreadyExistsException.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyExistsException extends RuntimeException{
	
	/**
	 * Instantiates a new account already exists exception.
	 *
	 * @param message the message
	 */
	public AccountAlreadyExistsException(String message) {
		super(message);
	}
}
