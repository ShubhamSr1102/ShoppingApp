package com.capgemini.order.service;

import com.capgemini.order.entity.Order;
import com.capgemini.order.exception.OrderNotFoundException;

public interface OrderService {

	public Order addOrder(Order order);

	public Order updateOrder(Order order)throws OrderNotFoundException;

	public Order findOrderById(int orderId) throws OrderNotFoundException;

	public void deleteOrder(Order order)throws OrderNotFoundException;
}
