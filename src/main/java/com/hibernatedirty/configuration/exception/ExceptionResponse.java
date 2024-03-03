package com.hibernatedirty.configuration.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ExceptionResponse {

	 private HttpStatus statusCode;
	    private String message;
	    
	    
}
