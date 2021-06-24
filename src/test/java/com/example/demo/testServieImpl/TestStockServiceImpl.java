package com.example.demo.testServieImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Stocks;
import com.example.demo.repository.StockRepository;
import com.example.demo.serviceImpl.StockServiceImp;

@SpringBootTest
public class TestStockServiceImpl {
	
	@Mock
	StockRepository stockRepository;

	@InjectMocks
	StockServiceImp stockServiceImp;
	
	@Test
	void testAddStock() {
		Stocks stock = new Stocks(1, "dell", 23465, "ele", 6);
		when(stockRepository.save(stock)).thenReturn(null);
		stockServiceImp.addStock(stock);
		verify(stockRepository, times(1)).save(stock);
	}
	
	@Test
	void testSortByProductName() {
		List<Stocks> stocks = new ArrayList<Stocks>();
		stocks.add(new Stocks(1, "dell", 23465, "ele", 6));
		stocks.add(new Stocks(2, "lenovo", 35465, "ele", 10));
		stocks.add(new Stocks(3, "apple", 123465, "ele", 3));
		when(stockRepository.findAll()).thenReturn(stocks);
		assertEquals(stocks, stockServiceImp.sortByProductName());
	}
	
	@Test
	void testGetSpecificStock() throws StockNameNotFound {
		Stocks stock = new Stocks(1, "dell", 23465, "ele", 6);
		String name = "dell";
		when(stockRepository.findByProductName(name)).thenReturn(stock);
		assertEquals(stock, stockServiceImp.getSpecificStock(name));
	}
	
}
