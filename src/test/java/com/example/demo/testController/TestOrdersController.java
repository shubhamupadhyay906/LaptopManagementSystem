package com.example.demo.testController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Controller.OrderController;
import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.model.Responses;
import com.example.demo.model.Stocks;
import com.example.demo.service.OrderService;

@SpringBootTest
public class TestOrdersController {

	@Mock
	OrderService orderService;

	@InjectMocks
	OrderController orderController;

	@Test
	void testAddNewOrder() throws StockNameNotFound {
		Customer customer = new Customer(1, null, null, null, null);
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 456, "ele", 4));
		Orders orders = new Orders(1, 4, customer, stocks);
		doNothing().when(orderService).addNewOrder(orders);
		ResponseEntity<Orders> responseEntity = new ResponseEntity<Orders>(orders, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		orderController.addNewOrder(orders);
		verify(orderService, times(1)).addNewOrder(orders);
	}

	@Test
	void testGetOrders() throws StockNameNotFound {
		Customer customer = new Customer(1, null, null, null, null);
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 456, "ele", 4));
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1, 4, customer, stocks));
		ResponseEntity<List<Orders>> responseEntity = new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
		when(orderService.getOrders()).thenReturn(orders);
		assertEquals(responseEntity, orderController.getOrders());
	}

}
