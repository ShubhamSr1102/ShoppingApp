package com.capgemini.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerAlreadyExist;
import com.capgemini.customer.exception.CustomerNotFoundException;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(value = AuthenticationFailedException.class)
	public ResponseEntity<Customer> authenticationFailedException(AuthenticationFailedException exception) {
		System.out.println(exception);
		System.out.println(exception.getCause());
		logger.info("Authentication Failed");
		logger.error("Authentication Failed", exception);
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerAlreadyExist.class)
	public ResponseEntity<Customer> customerAlreadyExist(CustomerAlreadyExist exception) {
		System.out.println(exception);
		System.out.println(exception.getCause());
		logger.info("Customer Already Exist");
		logger.error("Customer Already Exist", exception);
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Customer> customerNotFoundException(CustomerNotFoundException exception) {
		System.out.println(exception);
		System.out.println(exception.getCause());
		logger.info("Customer Not Found");
		logger.error("Customer Not Found", exception);
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
}
