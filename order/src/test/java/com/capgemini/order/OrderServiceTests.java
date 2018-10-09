package com.capgemini.order;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.capgemini.order.entity.Order;
import com.capgemini.order.repository.OrderRepository;
import com.capgemini.order.service.impl.OrderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	Order order;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		order = new Order();
		order.setOrderId(11);
	
	}

	@Test
	public void addOrderTest() throws Exception {
		when(orderRepository.save(Mockito.isA(Order.class))).thenReturn(order);
		assertEquals(order, orderServiceImpl.addOrder(order));
	}

	@Test
	public void updateOrderTest() throws Exception {
		order.setOrderId(12);
		when(orderRepository.save(Mockito.isA(Order.class))).thenReturn(order);
		assertEquals(order, orderServiceImpl.updateOrder(order));
	}

	@Test
	public void findOrderByIdTest() throws Exception {
		Optional<Order> optionalOrder = Optional.of(order);
		when(orderRepository.findById(Mockito.isA(Integer.class))).thenReturn(optionalOrder);
		assertEquals(optionalOrder.get(), orderServiceImpl.findOrderById(11));
	}

	@Test
	public void deleteOrderTest() throws Exception {
		orderServiceImpl.deleteOrder(order);
		verify(orderRepository).delete(order);
	}

}
