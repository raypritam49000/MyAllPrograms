package com.jdbc.client.rest.api.exception;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvisor {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleResourceNotFoundException(ResourceNotFoundException ex,HttpServletRequest request){
		return new ResponseEntity<Map<String,Object>>(Map.of("message",ex.getMessage(),"statusCode",404,"path",request.getRequestURL().toString(),"date",new Date()),HttpStatus.NOT_FOUND);
	}

}
