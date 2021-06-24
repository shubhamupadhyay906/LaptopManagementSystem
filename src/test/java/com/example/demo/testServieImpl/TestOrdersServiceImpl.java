package com.example.demo.testServieImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.model.Stocks;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.serviceImpl.OrderServiceImpl;

@SpringBootTest
public class TestOrdersServiceImpl {
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	StockRepository stockRepository;
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	
	@Test
	void testAddNewOrder() throws StockNameNotFound {
		Customer customer = new Customer(1, null, null, null, null);
		List<Stocks> stk = new ArrayList<Stocks>();
		stk.add(new Stocks(1, "dell", 456, "ele", 4));
		Orders orders = new Orders(1, 4, customer, stk);
		Stocks stock = new Stocks(1, "dell", 456, "ele", 4);
		when(stockRepository.findByProductName(orders.getStock().get(0).getProductName())).thenReturn(stock);
		when(orderRepository.save(orders)).thenReturn(null);
		orderServiceImpl.addNewOrder(orders);
		verify(orderRepository, times(1)).save(orders);
	}
	
	@Test
	void testGetOrders() {
		Customer customer = new Customer(1, null, null, null, null);
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 456, "ele", 4));
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1, 4, customer, stocks));
		when(orderRepository.findAll()).thenReturn(orders);
		assertEquals(orders, orderServiceImpl.getOrders());
	}
	

}
