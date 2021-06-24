package com.example.demo.testServieImpl;

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

import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.model.Stocks;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.serviceImpl.CustomerServiceImpl;

@SpringBootTest
public class TestCustomerServiceImpl {
	
	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Test
	void testAddNewCustomer() {
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 12456, "ele", 4));
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1, 4, null, stocks));
		Customer customer = new Customer(1, "shubham", "789546", "Hyd", orders);
		when(customerRepository.save(customer)).thenReturn(null);
		customerServiceImpl.addNewCustomer(customer);
		verify(customerRepository, times(1)).save(customer);
	}
	
	@Test
	void testGetCustomer(){
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 12456, "ele", 4));
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1, 4, null, stocks));
		List<Customer> customer = new ArrayList<Customer>();
		customer.add(new Customer(1, "shubham", "789546", "Hyd", orders));
		when(customerRepository.findAll()).thenReturn(customer);
		assertEquals(customer, customerServiceImpl.getCustomer());
	}
	
}
