package com.biocycle.redistributionCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RedistributionNotFoundException extends RuntimeException {
	public RedistributionNotFoundException(String message) {
		super(message);
	}
}
