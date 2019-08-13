package com.biocycle.giveAwayService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GiveAwayNotFoundException extends RuntimeException {
	
	public GiveAwayNotFoundException(String message) {
		super(message);
	}
}