package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.pets.NoFoodInfoFoundException;
import com.petshop.in.exceptions.pets.NoGroomingServicesFoundException;
import com.petshop.in.exceptions.pets.NoPetCategoryFoundException;
import com.petshop.in.exceptions.pets.NoSuppliersFoundException;
import com.petshop.in.exceptions.pets.NoTransactionsFoundException;
import com.petshop.in.exceptions.pets.NoVaccinationsFoundException;
import com.petshop.in.exceptions.pets.PetCannotBeAddedException;
import com.petshop.in.exceptions.pets.PetIdNotFoundException;
import com.petshop.in.exceptions.pets.PetsNotFoundException;

@ControllerAdvice
public class PetExceptionHandlers {

	@ExceptionHandler(PetsNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(PetsNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PetIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(PetIdNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoPetCategoryFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoPetCategoryFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoGroomingServicesFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoGroomingServicesFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoVaccinationsFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoVaccinationsFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoFoodInfoFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoFoodInfoFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuppliersFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoSuppliersFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoTransactionsFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoTransactionsFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PetCannotBeAddedException.class)
	public ResponseEntity<ErrorResponse> handleException(PetCannotBeAddedException ex)
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
