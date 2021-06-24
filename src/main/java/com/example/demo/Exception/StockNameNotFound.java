package com.example.demo.Exception;

public class StockNameNotFound extends Exception {
	
private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public StockNameNotFound(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

}
