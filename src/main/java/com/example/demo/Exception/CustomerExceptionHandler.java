package com.example.demo.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {
	
	@ExceptionHandler(CustomerNameNotFound.class)
	public ResponseEntity<ApiError> handleCustomerNameNotFoundException(CustomerNameNotFound customer){
		ApiError error=new ApiError(404,customer.getMessage(),new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGlobalNotFoundException(Exception ex){
		ApiError error=new ApiError(404,ex.getMessage(),new Date());
		return new ResponseEntity<ApiError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


}
