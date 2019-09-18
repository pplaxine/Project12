package com.biocycle.productBatchCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class ProductBatchNotFoundException.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductBatchNotFoundException extends RuntimeException{
	
	/**
	 * Instantiates a new product batch not found exception.
	 *
	 * @param message the message
	 */
	public ProductBatchNotFoundException(String message) {
		super(message);
	}
}
