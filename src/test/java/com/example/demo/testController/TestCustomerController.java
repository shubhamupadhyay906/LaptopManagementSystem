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

import com.example.demo.Controller.CustomerController;
import com.example.demo.Exception.CustomerNameNotFound;
import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.model.Responses;
import com.example.demo.model.Stocks;
import com.example.demo.service.CustomerService;


@SpringBootTest
public class TestCustomerController {
	
	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;
	
	@Test
	void testAddNewCustomer() throws CustomerNameNotFound {
		    
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 12456, "ele", 4));
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1, 4, null, stocks));
		Customer customer = new Customer(1, "shubham", "789546", "Hyd", orders);
		doNothing().when(customerService).addNewCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		customerController.addNewCustomer(customer);
		verify(customerService, times(1)).addNewCustomer(customer);
	}
	
	@Test
	void testGetCustomer() {
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 12456, "ele", 4));
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1, 4, null, stocks));
		List<Customer> customer = new ArrayList<Customer>();
		customer.add(new Customer(1, "shubham", "789546", "Hyd", orders));
		ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
		when(customerService.getCustomer()).thenReturn(customer);
		assertEquals(responseEntity, customerController.getCustomer());
	}
	
}
