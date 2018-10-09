package com.capgemini.mongodbapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.mongodbapp.entity.Product;
import com.capgemini.mongodbapp.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(productService.addProduct(product),
				HttpStatus.OK);
		logger.info("The product was successfully added");
		return responseEntity;
	}

	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		productService.findProductById(product.getProductId());
		return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable int productId) {
		Product productFromDb = productService.findProductById(productId);
		return new ResponseEntity<Product>(productFromDb, HttpStatus.OK);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		Product productFromDb = productService.findProductById(productId);
		productService.deleteProduct(productFromDb);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	@GetMapping("/products/name")
	public ResponseEntity<List<Product>> findProductByName(@RequestParam String productName) {
		return new ResponseEntity<List<Product>>(productService.findByProductName(productName), HttpStatus.OK);
	}
	
	@GetMapping("/products/category")
	public ResponseEntity<List<Product>> findProductByCategory(@RequestParam String productCategory) {
		return new ResponseEntity<List<Product>>(productService.findByProductCategory(productCategory), HttpStatus.OK);
	}
	
	@GetMapping("/products/range")
	public ResponseEntity<List<Product>> findByProductByRange(@RequestParam String productCategory, double min , double max) {
		return new ResponseEntity<List<Product>>(productService.findByProductByRange(productCategory, min, max), HttpStatus.OK);
	}
}
