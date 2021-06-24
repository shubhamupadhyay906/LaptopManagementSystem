package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Stocks;

public interface StockService {
	
	public void addStock(Stocks stock);
	
	public List<Stocks> sortByProductName();
	
	public Stocks getSpecificStock(String productName) throws StockNameNotFound;
	
}
