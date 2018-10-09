package com.capgemini.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.order.entity.Order;
import com.capgemini.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		ResponseEntity<Order> responseEntity = new ResponseEntity<Order>(orderService.addOrder(order), HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/order")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.OK);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable int orderId) {
		Order orderFromDb = orderService.findOrderById(orderId);
		return new ResponseEntity<Order>(orderFromDb, HttpStatus.OK);
	}

	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int orderId) {
		Order orderFromDb = orderService.findOrderById(orderId);
		orderService.deleteOrder(orderFromDb);
		return new ResponseEntity<Order>(HttpStatus.OK);
	}
}
