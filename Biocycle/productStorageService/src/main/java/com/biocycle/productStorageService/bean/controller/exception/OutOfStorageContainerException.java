package com.biocycle.productStorageService.bean.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class OutOfStorageContainerException extends RuntimeException{
	public OutOfStorageContainerException(String message) {
		super(message);
	}
}
