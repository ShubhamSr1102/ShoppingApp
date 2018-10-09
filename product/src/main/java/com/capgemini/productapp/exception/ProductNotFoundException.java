package com.capgemini.productapp.exception;

@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException {
	
	public ProductNotFoundException(String message) {
		super(message);
	}
}
