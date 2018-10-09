package com.capgemini.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.repository.CustomerRepository;
import com.capgemini.customer.service.impl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTests {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	Customer customer;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		customer = new Customer();
		customer.setCustomerId(11);
		customer.setCustomerName("Shubham");
		customer.setCustomerPassword("shubham");
		customer.setCustomerAddress("Dehradun");
		customer.setCustomerEmail("shubham@gmail.com");
	}

	@Test
	public void addCustomerTest()throws Exception {
		when(customerRepository.save(Mockito.isA(Customer.class))).thenReturn(customer);
		assertEquals(customer, customerServiceImpl.addCustomer(customer));
	}
	
	@Test
	public void editCustomerTest() throws Exception {
		customer.setCustomerName("Shubham Srivastava");
		Optional<Customer> optionalCustomer = Optional.of(customer);
		when(customerRepository.findById(11)).thenReturn(optionalCustomer);
		when(customerRepository.save(Mockito.isA(Customer.class))).thenReturn(customer);
		assertEquals(customer, customerServiceImpl.editCustomer(customer));
	}
	
	@Test
	public void findCustomerByIdTest() throws Exception {
		Optional<Customer> optionalCustomer = Optional.of(customer);
		when(customerRepository.findById(Mockito.isA(Integer.class))).thenReturn(optionalCustomer);
		assertEquals(optionalCustomer.get(), customerServiceImpl.getCustomerById(11));
	}
	
	@Test
	public void deleteCustomerTest() throws Exception {
		Optional<Customer> optionalCustomer = Optional.of(customer);
		when(customerRepository.findById(11)).thenReturn(optionalCustomer);
		customerServiceImpl.deleteCustomer(customer);
		verify(customerRepository).delete(customer);
		
	}

	@Test
	public void authenticate() throws Exception {
		Optional<Customer> optionalCustomer = Optional.of(customer);
		when(customerRepository.findById(11)).thenReturn(optionalCustomer);
		assertEquals(optionalCustomer.get(), customerServiceImpl.getCustomerById(11));
	}
	
	@Test
	public void getAllCustomers() throws Exception {
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		when(customerRepository.findAll()).thenReturn(customerList);
		assertEquals(customerList, customerServiceImpl.getAllCustomers());
	}
}
