package com.capgemini.order;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.capgemini.order.controller.OrderController;
import com.capgemini.order.entity.Order;
import com.capgemini.order.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTests {

	@Mock
	OrderService orderService;

	@InjectMocks
	OrderController orderController;

	MockMvc mockMvc;

	Order order;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		order = new Order();
		order.setOrderId(11);
		
	}

	@Test
	public void addOrderTest() throws Exception {

		when(orderService.addOrder(Mockito.isA(Order.class))).thenReturn(order);

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"orderId\":11, \"orderQuantity\":2, \"orderTotal\":35000 }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId").exists()).andExpect(jsonPath("$.orderQuantity").exists())
				.andExpect(jsonPath("$.orderTotal").exists()).andExpect(jsonPath("$.orderId").value(11)).andExpect(jsonPath("$.orderQuantity").value(2))
				.andExpect(jsonPath("$.orderTotal").value(35000)).andDo(print());
	}

	/*@Test
	public void findProductByIdTest() throws Exception {

		when(productService.findProductById(11)).thenReturn(product);

		mockMvc.perform(get("/products/11").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(11)).andExpect(jsonPath("$.productName").value("OnePlus"))
				.andExpect(jsonPath("$.productCategory").value("Mobile"))
				.andExpect(jsonPath("$.productPrice").value(35000)).andDo(print());
	}

	@Test
	public void updateProductTest() throws Exception {

		when(productService.findProductById(11)).thenReturn(product);

		product.setProductName("OnePlus 6T");

		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(product);

		mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"productId\":11, \"productName\": \"OnePlus 6T\", \"productPrice\":35000, \"productCategory\":\"Mobile\"  }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(11)).andExpect(jsonPath("$.productName").value("OnePlus 6T"))
				.andExpect(jsonPath("$.productCategory").value("Mobile"))
				.andExpect(jsonPath("$.productPrice").value(35000)).andDo(print());

		verify(productService).findProductById(product.getProductId());
	}

	@Test
	public void deleteProductTest() throws Exception {

		when(productService.findProductById(11)).thenReturn(product);

		mockMvc.perform(delete("/products/11").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print());

	}*/
}
