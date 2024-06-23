package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.groomingservices.ServiceAvailableException;
import com.petshop.in.exceptions.groomingservices.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingservices.ServiceInvalidInputException;
import com.petshop.in.exceptions.groomingservices.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingservices.ServicesNotFoundException;


@ControllerAdvice
public class GroomingServicesExceptionHandlers {

	@ExceptionHandler(ServicesNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ServicesNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ServiceIdNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceInvalidInputException.class)
	public ResponseEntity<ErrorResponse> handleException(ServiceInvalidInputException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceAvailableException.class)
	public ResponseEntity<ErrorResponse> handleException(ServiceAvailableException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceUnavailableException.class)
	public ResponseEntity<ErrorResponse> handleException(ServiceUnavailableException ex)
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
