package com.biocycle.productRequestCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class ConstrainTeaPotException extends RuntimeException{
	public ConstrainTeaPotException(String message) {
		super(message);
	}
}
