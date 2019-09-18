package com.biocycle.organisationCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionHandlerAdvice.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	/**
	 * Handle exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(OrganisationNotFoundException.class)
	public ResponseEntity handleException(OrganisationNotFoundException e) {
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(e.getMessage());
	}
}
