package com.biocycle.productStorageService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class OutOfStorageContainerException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class OutOfStorageContainerException extends RuntimeException{
	
	/**
	 * Instantiates a new out of storage container exception.
	 *
	 * @param message the message
	 */
	public OutOfStorageContainerException(String message) {
		super(message);
	}
}
