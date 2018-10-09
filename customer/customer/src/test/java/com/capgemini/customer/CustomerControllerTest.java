package com.capgemini.customer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.controller.CustomerController;
import com.capgemini.customer.entities.Customer;
import com.capgemini.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	private MockMvc mockMvc;

	Customer customer;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		customer = new Customer();
		customer.setCustomerId(11);
		customer.setCustomerName("Shubham");
		customer.setCustomerPassword("shubham");
		customer.setCustomerAddress("Dehradun");
		customer.setCustomerEmail("shubham@gmail.com");
	}

	@Test
	public void addCustomerTest() throws Exception {
		when(customerService.addCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\n" + "	\"customerId\" : 11,\n" + "	\"customerName\" : \"Shubham\",\n"
						+ "	\"customerEmail\" : \"shubham@gmail.com\",\n" + "	\"customerPassword\" : \"shubham\",\n"
						+ "	\"customerAddress\" : \"Dehradun\"\n" + "}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").exists()).andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.customerEmail").exists()).andExpect(jsonPath("$.customerPassword").exists())
				.andExpect(jsonPath("$.customerAddress").exists()).andDo(print());
	}

	@Test
	public void getCustomerByIdTest() throws Exception {

		when(customerService.getCustomerById(11)).thenReturn(customer);

		mockMvc.perform(get("/customers/11").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").exists()).andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.customerEmail").exists()).andExpect(jsonPath("$.customerPassword").exists())
				.andExpect(jsonPath("$.customerAddress").exists()).andExpect(jsonPath("$.customerId").value(11))
				.andExpect(jsonPath("$.customerName").value("Shubham"))
				.andExpect(jsonPath("$.customerEmail").value("shubham@gmail.com"))
				.andExpect(jsonPath("$.customerPassword").value("shubham"))
				.andExpect(jsonPath("$.customerAddress").value("Dehradun")).andDo(print());
	}

	@Test
	public void editCustomerTest() throws Exception {

		when(customerService.getCustomerById(11)).thenReturn(customer);

		customer.setCustomerName("Shubham Srivastava");

		when(customerService.editCustomer(Mockito.isA(Customer.class))).thenReturn(customer);

		mockMvc.perform(put("/customer").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\n" + "	\"customerId\" : 11,\n" + "	\"customerName\" : \"Shubham Srivastava\",\n"
						+ "	\"customerEmail\" : \"shubham@gmail.com\",\n" + "	\"customerPassword\" : \"shubham\",\n"
						+ "	\"customerAddress\" : \"Dehradun\"\n" + "}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").exists()).andExpect(jsonPath("$.customerName").exists())
				.andExpect(jsonPath("$.customerEmail").exists()).andExpect(jsonPath("$.customerPassword").exists())
				.andExpect(jsonPath("$.customerAddress").exists()).andExpect(jsonPath("$.customerId").value(11))
				.andExpect(jsonPath("$.customerName").value("Shubham Srivastava"))
				.andExpect(jsonPath("$.customerEmail").value("shubham@gmail.com"))
				.andExpect(jsonPath("$.customerPassword").value("shubham"))
				.andExpect(jsonPath("$.customerAddress").value("Dehradun")).andDo(print());

		verify(customerService).getCustomerById(customer.getCustomerId());
	}

	@Test
	public void deleteCustomerTest() throws Exception {

		when(customerService.getCustomerById(11)).thenReturn(customer);

		mockMvc.perform(delete("/customers/11").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	public void authenticateTest() throws Exception {

		when(customerService.authentication(Mockito.isA(Customer.class))).thenReturn(customer);

		mockMvc.perform(get("/customer/id=11+&+password=shubham").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.customerName").exists()).andExpect(jsonPath("$.customerEmail").exists())
				.andExpect(jsonPath("$.customerPassword").exists()).andExpect(jsonPath("$.customerAddress").exists())
				.andExpect(jsonPath("$.customerId").value(11)).andExpect(jsonPath("$.customerName").value("Shubham"))
				.andExpect(jsonPath("$.customerEmail").value("shubham@gmail.com"))
				.andExpect(jsonPath("$.customerPassword").value("shubham"))
				.andExpect(jsonPath("$.customerAddress").value("Dehradun")).andDo(print());
	}

	@Test
	public void getAllCustomersTest() throws Exception {

		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		System.out.println(customerList);
		when(customerService.getAllCustomers()).thenReturn(customerList);

		mockMvc.perform(get("/customers").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
	}
}
