package com.learn.rest.webservices.restwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learn.rest.webservices.restwebservices.user.UserNotFoundException;


//COntroller to sent back the exception
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		
		ExceptionResponse exception =  new ExceptionResponse(new Date(),"Exception",request.getDescription(false));
		return new ResponseEntity(exception,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
		
		ExceptionResponse exception =  new ExceptionResponse(new Date(),"Exception",request.getDescription(false));
		return new ResponseEntity(exception,HttpStatus.NOT_FOUND);
		
	}

}
