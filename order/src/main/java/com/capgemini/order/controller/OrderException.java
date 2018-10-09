package com.capgemini.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exception.OrderNotFoundException;

@ControllerAdvice
public class OrderException {

	private static final Logger logger = LoggerFactory.getLogger(OrderException.class);
		
		@ExceptionHandler(value = OrderNotFoundException.class)
		public ResponseEntity<Order> orderNotFoundException(OrderNotFoundException exception) {
			System.out.println(exception);
			System.out.println(exception.getCause());
			logger.info("Order does not exist");
			logger.error("Order does not exist", exception);
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}

}
