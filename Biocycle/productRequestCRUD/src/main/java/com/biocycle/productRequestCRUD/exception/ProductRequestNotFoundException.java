package com.biocycle.productRequestCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ProductRequestNotFoundException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductRequestNotFoundException extends RuntimeException{
	
	/**
	 * Instantiates a new product request not found exception.
	 *
	 * @param message the message
	 */
	public ProductRequestNotFoundException(String message) {
		super(message);
	}
}
