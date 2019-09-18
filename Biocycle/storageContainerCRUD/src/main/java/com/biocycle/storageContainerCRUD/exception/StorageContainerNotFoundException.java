package com.biocycle.storageContainerCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class StorageContainerNotFoundException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StorageContainerNotFoundException extends RuntimeException{
	
	/**
	 * Instantiates a new storage container not found exception.
	 *
	 * @param message the message
	 */
	public StorageContainerNotFoundException(String message) {
		super(message);
	}
}
