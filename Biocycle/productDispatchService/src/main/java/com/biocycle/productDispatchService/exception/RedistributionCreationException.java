package com.biocycle.productDispatchService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class RedistributionCreationException extends RuntimeException {
	public RedistributionCreationException(String message) {
		super(message);
	}
}
