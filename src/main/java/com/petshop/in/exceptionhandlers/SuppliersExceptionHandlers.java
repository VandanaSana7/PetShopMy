package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.suppliers.SupplierCityNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.suppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierStateNotFoundException;
import com.petshop.in.exceptions.suppliers.SuppliersNotFoundException;

@ControllerAdvice
public class SuppliersExceptionHandlers {

	@ExceptionHandler(SuppliersNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(SuppliersNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SupplierIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(SupplierIdNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SupplierNameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(SupplierNameNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SupplierCityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(SupplierCityNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SupplierStateNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(SupplierStateNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SupplierInputInvalidException.class)
	public ResponseEntity<ErrorResponse> handleException(SupplierInputInvalidException ex)
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
