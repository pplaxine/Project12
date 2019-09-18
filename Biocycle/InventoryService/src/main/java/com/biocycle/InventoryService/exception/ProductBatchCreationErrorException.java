package com.biocycle.InventoryService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ProductBatchCreationErrorException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class ProductBatchCreationErrorException extends RuntimeException {
	
	/**
	 * Instantiates a new product batch creation error exception.
	 *
	 * @param message the message
	 */
	public ProductBatchCreationErrorException(String message) {
		super(message);
	}
}
