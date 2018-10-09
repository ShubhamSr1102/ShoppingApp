package com.capgemini.productapp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductControllerTest {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	private MockMvc mockMvc;

	Product product;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		product = new Product();
		product.setProductId(11);
		product.setProductName("OnePlus");
		product.setProductPrice(35000);
		product.setProductCategory("Mobile");
	}

	@Test
	public void addProductTest() throws Exception {

		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(product);

		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"productId\":11, \"productName\": \"OnePlus\", \"productPrice\":35000, \"productCategory\":\"Mobile\"  }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andDo(print());
	}

	@Test
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
	public void deleteProductTest() throws Exception{
		
		when(productService.findProductById(11)).thenReturn(product);
		
		mockMvc.perform(delete("/products/11")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	
	}
}
