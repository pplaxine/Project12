package com.biocycle.InventoryService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class ProductBatchCreationErrorException extends RuntimeException {
	public ProductBatchCreationErrorException(String message) {
		super(message);
	}
}
