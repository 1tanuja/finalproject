package com.fooddelivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class UnauthorizedAccessException extends RuntimeException{
	public UnauthorizedAccessException() {
        super("Unauthorized access.");
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
	

}
