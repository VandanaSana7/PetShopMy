package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.customers.CustomerCannotBeAddedException;
import com.petshop.in.exceptions.customers.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customers.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customers.CustomerFirstLastNameNotFoundException;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customers.CustomerNotFoundException;
import com.petshop.in.exceptions.customers.CustomerStateNotFoundException;
import com.petshop.in.exceptions.customers.CustomerTransactionCustomerIdNotFoundException;
import com.petshop.in.exceptions.customers.CustomerTransactionStatusNotFoundException;


@ControllerAdvice
public class CustomerExceptionHandlers {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerIdNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerCityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerCityNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerStateNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerStateNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerFirstLastNameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerFirstLastNameNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerTransactionCustomerIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerTransactionCustomerIdNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerTransactionStatusNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerTransactionStatusNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerCannotBeAddedException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerCannotBeAddedException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerCannotBeUpdatedException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerCannotBeUpdatedException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MismatchDataTypeException.class)
	public ResponseEntity<ErrorResponse> handleException(MismatchDataTypeException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
}
