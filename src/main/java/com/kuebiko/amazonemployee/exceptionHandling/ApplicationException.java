package com.kuebiko.amazonemployee.exceptionHandling;

public class ApplicationException extends RuntimeException{
	
	public ApplicationException(String message) {
		super(message);
	}

}
