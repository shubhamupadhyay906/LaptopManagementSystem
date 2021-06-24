package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.StockNameNotFound;
import com.example.demo.model.Responses;
import com.example.demo.model.Stocks;
import com.example.demo.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping("/addNewProduct")
	public Responses addStock(@RequestBody Stocks stock) throws StockNameNotFound {
		if (stock.getProductName().equals("") || stock.getProductName() == null || stock.getPerPrice() == 0
				||stock.getType().equals("")||stock.getType()==null) {
			throw new StockNameNotFound("Should not be empty");
		}

		this.stockService.addStock(stock);
		log.info("save successfully " + stock.getProductName());
		ResponseEntity<Stocks> responseEntity = new ResponseEntity<Stocks>(stock, HttpStatus.CREATED);
		Responses response = new Responses("success", responseEntity);
		return response;
	}

	@GetMapping("/sortByProductName")
	public ResponseEntity<List<Stocks>> sortByProductName() throws StockNameNotFound {
		 List<Stocks> sortStock = stockService.sortByProductName();
		 if(sortStock.isEmpty()) {
			 log.error("Error occured");
			 throw new StockNameNotFound("stock list empty");
		 }
		 else {
			 log.info("Retrived");
			 List<Stocks> sorted = sortStock.stream().sorted(new Stocks()::compare).collect(Collectors.toList());
			 return new ResponseEntity<List<Stocks>>(sorted,HttpStatus.OK);
		 }
	}
	
	@GetMapping("/getStocks/{productName}")
	public ResponseEntity<Stocks> getSpecificStock(@PathVariable String productName) throws StockNameNotFound {
		if(productName==null || productName.equals("")) {
			throw new StockNameNotFound("Stock with this particular name not found"+productName);
		}
		Stocks stock= this.stockService.getSpecificStock(productName);
		log.info("Get By Name "+productName);
		return new ResponseEntity<Stocks>(stock,HttpStatus.OK);
	}
	
}
