package com.biocycle.organisationCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class OrganisationNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrganisationNotFoundException extends RuntimeException{
	
	/**
	 * Instantiates a new organisation not found exception.
	 *
	 * @param message the message
	 */
	public OrganisationNotFoundException(String message) {
		super(message);
	}
}
