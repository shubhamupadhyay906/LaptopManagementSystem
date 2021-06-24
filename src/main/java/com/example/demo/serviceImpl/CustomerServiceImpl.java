package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private	CustomerRepository customerRepository;
	
	@Override
	public void addNewCustomer(Customer customer) {
		// TODO Auto-generated method stub
		log.info("In customer Service class");
		customerRepository.save(customer);
	}

	@Override
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		log.info("In customer Service class");
		return customerRepository.findAll();
		
	}

}
