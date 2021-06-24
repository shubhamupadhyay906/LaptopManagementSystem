package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Stocks;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.StockService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImp implements StockService{

	@Autowired
	private	StockRepository stockRepository;

	@Override
	public void addStock(Stocks stock) {
		// TODO Auto-generated method stub
		log.info("In stock Service class");
		stockRepository.save(stock);
	}


	@Override
	@Cacheable(cacheNames = "sortedData")
	public List<Stocks> sortByProductName() {
		log.info("from db");
		return stockRepository.findAll();
	}

	@Override
	@Cacheable(cacheNames = "stock", key = "#productName")
	public Stocks getSpecificStock(String productName) throws StockNameNotFound {
		Optional<Stocks> stock=Optional.ofNullable(stockRepository.findByProductName(productName));
		log.info("In stock Service class");
		if(stock.isPresent()) {
			log.info("Retrieved from db");
			Stocks stocks=stock.get();
			return stocks;
		}else {
			log.error("error occured");
			throw new StockNameNotFound("Data with that name not found "+productName);
		}
	}

}
