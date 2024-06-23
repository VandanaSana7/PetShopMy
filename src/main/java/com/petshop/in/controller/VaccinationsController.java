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
import com.petshop.in.exceptions.vaccinations.VaccinationIdNotFoundException;
import com.petshop.in.exceptions.vaccinations.VaccinationInputInvalidException;
import com.petshop.in.exceptions.vaccinations.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;
import com.petshop.in.serviceimpl.VaccinationServiceImpl;

@RestController
@RequestMapping("/api/v1/vaccinations")
public class VaccinationsController {
	
	@Autowired
	private VaccinationServiceImpl vaccinationService;
	
	@GetMapping
	public ResponseEntity<List<Vaccinations>> getAllVaccinations() throws VaccinationsNotFoundException
	{
		return new ResponseEntity<>(vaccinationService.getAllVaccinations(), HttpStatus.OK);
	}
	
	@GetMapping("/{vaccination_id}")
	public ResponseEntity<Vaccinations> getVaccinationsByVaccinationId(@PathVariable("vaccination_id") int vaccination_id) throws VaccinationIdNotFoundException
	{
		return new ResponseEntity<>(vaccinationService.getVaccinationsByVaccinationId(vaccination_id), HttpStatus.OK);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<Vaccinations>> getAllAvailableVaccinations()
	{
		return new ResponseEntity<>(vaccinationService.getAllAvailableVaccinations(), HttpStatus.OK);
	}
	
	@GetMapping("/unavailable")
	public ResponseEntity<List<Vaccinations>> getAllUnavailableVaccinations()
	{
		return new ResponseEntity<>(vaccinationService.getAllUnavailableVaccinations(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addVaccinations(@RequestBody Vaccinations vaccination) throws VaccinationInputInvalidException, MismatchDataTypeException
	{
		return new ResponseEntity<>(vaccinationService.addVaccinations(vaccination), HttpStatus.OK);
	}
	
	@PutMapping("/update/{vaccination_id}")
	public ResponseEntity<SuccessResponse> updateVaccinationsByVaccinationId(@PathVariable("vaccination_id") int vaccination_id, @RequestBody Vaccinations vaccination) throws VaccinationIdNotFoundException, MismatchDataTypeException
	{
		return new ResponseEntity<>(vaccinationService.updateVaccinationsByVaccinationId(vaccination_id, vaccination), HttpStatus.OK);
	}
	
}
