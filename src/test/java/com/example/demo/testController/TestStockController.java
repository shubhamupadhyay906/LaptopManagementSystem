package com.example.demo.testController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Controller.StockController;
import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Customer;
import com.example.demo.model.Responses;
import com.example.demo.model.Stocks;
import com.example.demo.service.StockService;

@SpringBootTest
public class TestStockController {

	@Mock
	StockService stockService;

	@InjectMocks
	StockController stockController;

	@Test
	void testAddStock() throws StockNameNotFound {
		Stocks stock = new Stocks(1, "dell", 23465, "ele", 6);
		doNothing().when(stockService).addStock(stock);
		ResponseEntity<Stocks> responseEntity = new ResponseEntity<Stocks>(stock, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		stockController.addStock(stock);
		verify(stockService, times(1)).addStock(stock);
	}

	@Test
	void testSortByProductName() throws StockNameNotFound {
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 23465, "ele", 6));
		stocks.add(new Stocks(2, "lenovo", 35465, "ele", 10));
		stocks.add(new Stocks(3, "apple", 123465, "ele", 3));
		List<Stocks> sorted = stocks.stream().sorted(new Stocks()::compare).collect(Collectors.toList());
		ResponseEntity<List<Stocks>> responseEntity = new ResponseEntity<List<Stocks>>(sorted, HttpStatus.OK);
		when(stockService.sortByProductName()).thenReturn(sorted);
		assertEquals(responseEntity, stockController.sortByProductName());
	}
	
	@Test
	void testGetSpecificStock() throws StockNameNotFound {
		Stocks stock = new Stocks(1, "dell", 23465, "ele", 6);
		String name = "dell";
		ResponseEntity<Stocks> responseEntity = new ResponseEntity<Stocks>(stock, HttpStatus.OK);
		when(stockService.getSpecificStock(name)).thenReturn(stock);
		assertEquals(responseEntity,stockController.getSpecificStock(name));
	}

}
