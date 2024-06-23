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
import com.petshop.in.exceptions.pets.NoFoodInfoFoundException;
import com.petshop.in.exceptions.pets.NoGroomingServicesFoundException;
import com.petshop.in.exceptions.pets.NoPetCategoryFoundException;
import com.petshop.in.exceptions.pets.NoSuppliersFoundException;
import com.petshop.in.exceptions.pets.NoTransactionsFoundException;
import com.petshop.in.exceptions.pets.NoVaccinationsFoundException;
import com.petshop.in.exceptions.pets.PetCannotBeAddedException;
import com.petshop.in.exceptions.pets.PetIdNotFoundException;
import com.petshop.in.exceptions.pets.PetsNotFoundException;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;
import com.petshop.in.serviceimpl.PetServiceImpl;

@RestController
@RequestMapping("api/v1/pets")
public class PetsController {
	
	@Autowired
	private PetServiceImpl petService;
	
	@GetMapping
	public ResponseEntity<List<Pets>> getAllPets() throws PetsNotFoundException
	{
		return new ResponseEntity<>(petService.getAllPets(), HttpStatus.OK);
	}
	
	@GetMapping("/{pet_id}")
	public ResponseEntity<Pets> getPetsById(@PathVariable("pet_id") int pet_id) throws PetIdNotFoundException
	{
		return new ResponseEntity<>(petService.getPetsById(pet_id), HttpStatus.OK);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Pets>> getPetsByCategory(@PathVariable("category") String category) throws NoPetCategoryFoundException
	{
		return new ResponseEntity<>(petService.getPetsByCategory(category), HttpStatus.OK);
	}
	
	@GetMapping("/grooming_services/{pet_id}")
	public ResponseEntity<Object> getPetsByGroomingServices(@PathVariable("pet_id") int pet_id) throws NoGroomingServicesFoundException, PetIdNotFoundException
	{
		return new ResponseEntity<>(petService.getPetsByGroomingServices(pet_id), HttpStatus.OK);
	}
	
	@GetMapping("/vaccinations/{pet_id}")
	public ResponseEntity<List<Object>> getPetsByVaccinations(@PathVariable("pet_id") int pet_id) throws NoVaccinationsFoundException, PetIdNotFoundException
	{
		return new ResponseEntity<>(petService.getPetsByVaccinations(pet_id), HttpStatus.OK);
	}
	
	@GetMapping("/food_info/{pet_id}")
	public ResponseEntity<List<Object[]>> getPetsByPetFood(@PathVariable("pet_id") int pet_id) throws NoFoodInfoFoundException, PetIdNotFoundException
	{
		return new ResponseEntity<>(petService.getPetsByPetFood(pet_id), HttpStatus.OK);
	}
	
	@GetMapping("/suppliers/{pet_id}")
	public ResponseEntity<List<Object[]>> getSupplierByPetId(@PathVariable("pet_id") int pet_id) throws NoSuppliersFoundException, PetIdNotFoundException
	{
		return new ResponseEntity<>(petService.getSupplierByPetId(pet_id), HttpStatus.OK);
	}
	
	@GetMapping("/transaction_history/{pet_id}")
	public ResponseEntity<Transactions> getTransactionByPetId(@PathVariable("pet_id") int pet_id) throws NoTransactionsFoundException, PetIdNotFoundException
	{
		return new ResponseEntity<>(petService.getTransactionByPetId(pet_id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addPets(@RequestBody Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException
	{
		return new ResponseEntity<>(petService.addPets(pet), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{pet_id}")
	public ResponseEntity<SuccessResponse> updatePetsByPetId(@RequestBody Pets pet, @PathVariable("pet_id") int pet_id) throws PetIdNotFoundException, MismatchDataTypeException
	{
		return new ResponseEntity<>(petService.updatePetsByPetId(pet, pet_id), HttpStatus.OK);
	}
	
}
