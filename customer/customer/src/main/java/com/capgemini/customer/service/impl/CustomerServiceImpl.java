package com.capgemini.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerAlreadyExist;
import com.capgemini.customer.exception.CustomerNotFoundException;
import com.capgemini.customer.repository.CustomerRepository;
import com.capgemini.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExist {

		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		if (!optionalCustomer.isPresent()) {
			return customerRepository.save(customer);
		}

		throw new CustomerAlreadyExist(
				"Registration failed!! Customer Already Exists in this id" + customer.getCustomerId());
	}

	@Override
	public Customer authentication(Customer customer) throws CustomerNotFoundException, AuthenticationFailedException {

		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		if (optionalCustomer.isPresent()) {
			if (optionalCustomer.get().getCustomerPassword().equals(customer.getCustomerPassword())) {
				return optionalCustomer.get();
			} else {
				throw new AuthenticationFailedException("Password is Wrong");
			}

		}
		throw new CustomerNotFoundException("Invalid Id!, Customer does not exist");

	}

	@Override
	public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isPresent()) {
			return optionalCustomer.get();
		}
		throw new CustomerNotFoundException("Invalid Id!, Customer does not exist");

	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void deleteCustomer(Customer customer) throws CustomerNotFoundException {

		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		if (optionalCustomer.isPresent()) {
			customerRepository.delete(customer);
		}
		else
		throw new CustomerNotFoundException("Deletion Failed! Customer does not exist");
	}

	@Override
	public Customer editCustomer(Customer customer) throws CustomerNotFoundException {
		Optional<Customer> customerFromDb = customerRepository.findById(customer.getCustomerId());
		if (customerFromDb.isPresent()) {
			return customerRepository.save(customer);
		}
		throw new CustomerNotFoundException("Edition failed!!  Customer does not exist" + customer.getCustomerId());

	}

}
