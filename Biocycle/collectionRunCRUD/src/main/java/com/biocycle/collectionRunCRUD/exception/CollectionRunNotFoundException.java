package com.biocycle.collectionRunCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CollectionRunNotFoundException extends RuntimeException {
	
	public CollectionRunNotFoundException(String message) {
		super(message);
	}
}
