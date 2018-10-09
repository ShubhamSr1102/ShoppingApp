package com.capgemini.customer.exception;

@SuppressWarnings("serial")
public class AuthenticationFailedException extends RuntimeException {

	public AuthenticationFailedException(String message) {
		super(message);
	}
}
