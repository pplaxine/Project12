package com.biocycle.redistributionCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class RedistributionNotFoundException extends RuntimeException {
	public RedistributionNotFoundException(String message) {
		super(message);
	}
}
