package com.petshop.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingservices.ServiceAvailableException;
import com.petshop.in.exceptions.groomingservices.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingservices.ServiceInvalidInputException;
import com.petshop.in.exceptions.groomingservices.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingservices.ServicesNotFoundException;
import com.petshop.in.model.GroomingServices;
import com.petshop.in.serviceimpl.GroomingServicesServiceImpl;

@RestController
@RequestMapping("/api/v1/services")
public class GroomingServicesController {
	
	@Autowired
	GroomingServicesServiceImpl groomingService;
	
	@GetMapping()
	public ResponseEntity<List<GroomingServices>> getAllGroomingServices() throws ServicesNotFoundException
	{
		return new ResponseEntity<>(groomingService.getAllGroomingServices(), HttpStatus.OK);
	}
	
	@GetMapping("/{service_id}")
	public ResponseEntity<GroomingServices> getGroomingServicesByServiceId(@PathVariable("service_id") int service_id) throws ServiceIdNotFoundException
	{
		return new ResponseEntity<>(groomingService.getGroomingServicesByServiceId(service_id), HttpStatus.OK);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<GroomingServices>> getAllAvailableGroomingServices() throws ServiceAvailableException
	{
		return new ResponseEntity<>(groomingService.getAllAvailableGroomingServices(), HttpStatus.OK);
	}
	
	@GetMapping("/unavailable")
	public ResponseEntity<List<GroomingServices>> getAllUnavailableGroomingServices() throws ServiceUnavailableException
	{
		return new ResponseEntity<>(groomingService.getAllUnavailableGroomingServices(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addGroomingServices(@RequestBody GroomingServices service) throws ServiceInvalidInputException, MismatchDataTypeException
	{
		return new ResponseEntity<>(groomingService.addGroomingServices(service), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{service_id}")
	public ResponseEntity<SuccessResponse> updateGroomingServicesByServiceId(@RequestBody GroomingServices service, @PathVariable("service_id") Integer service_id) throws ServiceIdNotFoundException, MismatchDataTypeException
	{
		return new ResponseEntity<>(groomingService.updateGroomingServicesByServiceId(service, service_id), HttpStatus.OK);
	}
}
