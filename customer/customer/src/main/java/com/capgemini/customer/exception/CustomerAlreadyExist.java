package com.capgemini.customer.exception;

@SuppressWarnings("serial")
public class CustomerAlreadyExist extends RuntimeException {

	public CustomerAlreadyExist(String message) {
		super(message);
	}
}
