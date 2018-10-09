package com.capgemini.order.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Order {

	@Id
	private int orderId;
	private int productId;
	private int customerId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate customerDateOfBirth;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(int orderId, int productId, int customerId, LocalDate customerDateOfBirth) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.customerId = customerId;
		this.customerDateOfBirth = customerDateOfBirth;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productId=" + productId + ", customerId=" + customerId
				+ ", customerDateOfBirth=" + customerDateOfBirth + "]";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public LocalDate getCustomerDateOfBirth() {
		return customerDateOfBirth;
	}

	public void setCustomerDateOfBirth(LocalDate customerDateOfBirth) {
		this.customerDateOfBirth = customerDateOfBirth;
	}

}
