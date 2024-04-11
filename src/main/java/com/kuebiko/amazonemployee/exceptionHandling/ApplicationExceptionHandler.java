package com.kuebiko.amazonemployee.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice // allows us to handle exceptions across whole application
public class ApplicationExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<?> handleApplicationException(ApplicationException applicationException){
		ErrorDetails errorDetails = new ErrorDetails(applicationException.getMessage(), new Date());
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}
}
