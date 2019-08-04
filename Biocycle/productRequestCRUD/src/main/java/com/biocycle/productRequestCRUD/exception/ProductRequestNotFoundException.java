package com.biocycle.productRequestCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductRequestNotFoundException extends RuntimeException{
	public ProductRequestNotFoundException(String message) {
		super(message);
	}
}
