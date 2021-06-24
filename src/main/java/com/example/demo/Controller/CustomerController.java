package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.CustomerNameNotFound;
import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Customer;
import com.example.demo.model.Responses;
import com.example.demo.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/addcustomer")
	public Responses addNewCustomer(@RequestBody Customer customer) throws CustomerNameNotFound {
		if (customer.getPhoneNumber().equals("") || customer.getAddress().equals("") 
				|| customer.getName() == null || customer.getName().equals("")) {
			throw new CustomerNameNotFound("fields can't be empty");
		}
		log.info("save successfully" + customer.getName());
		this.customerService.addNewCustomer(customer);
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		return response;
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getCustomer() {
		List<Customer> cus = this.customerService.getCustomer();
		log.info("Data retrived");
		return new ResponseEntity<List<Customer>>(cus, HttpStatus.OK);
	}

}
