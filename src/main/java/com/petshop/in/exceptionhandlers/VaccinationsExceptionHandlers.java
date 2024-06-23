package com.petshop.in.exceptionhandlers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.petshop.in.exceptions.ErrorResponse;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.vaccinations.VaccinationIdNotFoundException;
import com.petshop.in.exceptions.vaccinations.VaccinationInputInvalidException;
import com.petshop.in.exceptions.vaccinations.VaccinationsNotFoundException;

@ControllerAdvice
public class VaccinationsExceptionHandlers {

	@ExceptionHandler(VaccinationsNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(VaccinationsNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(VaccinationIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(VaccinationIdNotFoundException ex)
	{
		ErrorResponse err = new ErrorResponse();
		err.setMessage(ex.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		err.setTimeStamp(LocalDate.now());
		
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(VaccinationInputInvalidException.class)
	public ResponseEntity<ErrorResponse> handleException(VaccinationInputInvalidException ex)
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
