package com.capgemini.productapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<Product> productNotFoundException(ProductNotFoundException exception) {
		System.out.println(exception);
		System.out.println(exception.getCause());
		logger.info("Product Does Not Exist");
		logger.error("Product Does Not Exist", exception);
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
}
