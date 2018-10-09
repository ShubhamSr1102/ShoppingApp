package com.capgemini.customer.controller;

import java.util.List;

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

import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerNotFoundException;
import com.capgemini.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer),
				HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("/customer")
	public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) {
		Customer customerFromDb = customerService.getCustomerById(customer.getCustomerId());
		if (customerFromDb != null)
			return new ResponseEntity<Customer>(customerService.editCustomer(customer), HttpStatus.OK);
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
		Customer customerFromDb = customerService.getCustomerById(customerId);
		if (customerFromDb != null)
			return new ResponseEntity<Customer>(customerFromDb, HttpStatus.OK);
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
		Customer customerFromDb = customerService.getCustomerById(customerId);
		if (customerFromDb != null) {
			customerService.deleteCustomer(customerFromDb);
			return new ResponseEntity<Customer>(HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/customer/id={customerId}+&+password={customerPassword}")
	public ResponseEntity<Customer> authentication(@PathVariable int customerId, @PathVariable String customerPassword) throws CustomerNotFoundException, AuthenticationFailedException {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPassword(customerPassword);
		return new ResponseEntity<Customer>(customerService.authentication(customer), HttpStatus.OK);
	}
}
