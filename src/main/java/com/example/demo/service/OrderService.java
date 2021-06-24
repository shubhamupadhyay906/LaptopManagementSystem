package com.example.demo.service;

import java.util.List;

import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Orders;

public interface OrderService {
	
	
public void addNewOrder(Orders orders) throws StockNameNotFound;
	
	public List<Orders> getOrders();
	

}
