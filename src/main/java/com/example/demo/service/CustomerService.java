package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Stocks;

@Repository
public interface CustomerService {
	
	public void addNewCustomer(Customer customer);
	
	public List<Customer> getCustomer();

}
