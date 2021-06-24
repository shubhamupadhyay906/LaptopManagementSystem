package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.OrderController;
import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Orders;
import com.example.demo.model.Stocks;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.service.OrderService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	StockRepository stockRepository;
	

	@Override
	public void addNewOrder(Orders orders) throws StockNameNotFound {
		
		Optional<Stocks> stock=Optional.ofNullable(stockRepository.findByProductName(orders.getStock().get(0).getProductName()));
		if(stock.isPresent()) {
			Stocks stocks = stock.get();
			if(stocks.getTotalStock()<orders.getQuantity()) {
				log.error("error");
				throw new StockNameNotFound("Entered less quantity then "+stocks.getTotalStock());
			}else {
				int totalStock = stocks.getTotalStock()-orders.getQuantity();
				stocks.setTotalStock(totalStock);
				stockRepository.save(stocks);
			}
		}
		orders.getStock().get(0).setProductId(stock.get().getProductId());
		orderRepository.save(orders);
	}
	

	@Override
	@Cacheable(cacheNames = "Orders")
	public List<Orders> getOrders() {
		log.info("from db");
		return orderRepository.findAll();
	}

}
