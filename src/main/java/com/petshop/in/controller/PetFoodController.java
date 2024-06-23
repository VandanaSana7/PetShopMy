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
import com.petshop.in.exceptions.petfood.PetFoodAddException;
import com.petshop.in.exceptions.petfood.PetFoodBrandNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodIdNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodNameNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodQuantityNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodTypeNotFoundException;
import com.petshop.in.model.PetFood;
import com.petshop.in.serviceimpl.PetFoodServiceImpl;

@RestController
@RequestMapping("/api/v1/pet_foods")
public class PetFoodController {
	
	@Autowired
	PetFoodServiceImpl petFoodService;
	
	@GetMapping
	public ResponseEntity<List<PetFood>> getAllPetFood() throws PetFoodNotFoundException
	{
		return new ResponseEntity<>(petFoodService.getAllPetFood(), HttpStatus.OK);
	}
	
	@GetMapping("/{food_id}")
	public ResponseEntity<PetFood> getPetFoodByFoodId(@PathVariable("food_id") int food_id) throws PetFoodIdNotFoundException
	{
		return new ResponseEntity<>(petFoodService.getPetFoodByFoodId(food_id), HttpStatus.OK);
	}
	
	@GetMapping("/name/{food_name}")
	public ResponseEntity<List<PetFood>> getPetFoodByFoodName(@PathVariable("food_name") String food_name) throws PetFoodNameNotFoundException
	{
		return new ResponseEntity<>(petFoodService.getPetFoodByFoodName(food_name), HttpStatus.OK);
	}
	
	@GetMapping("/food_type/{type}")
	public ResponseEntity<List<PetFood>> getPetFoodByFoodType(@PathVariable("type") String food_type) throws PetFoodTypeNotFoundException
	{
		return new ResponseEntity<>(petFoodService.getPetFoodByFoodType(food_type), HttpStatus.OK);
	}
	
	@GetMapping("/brand/{brand_name}")
	public ResponseEntity<List<PetFood>> getPetFoodByBrandName(@PathVariable("brand_name") String brand_name) throws PetFoodBrandNotFoundException
	{
		return new ResponseEntity<>(petFoodService.getPetFoodByBrandName(brand_name), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addPetFood(@RequestBody PetFood petFood) throws PetFoodAddException, MismatchDataTypeException
	{
		return new ResponseEntity<>(petFoodService.addPetFood(petFood), HttpStatus.OK);
	}
	
	@PutMapping("/update/{food_id}")
	public ResponseEntity<SuccessResponse> updatePetFood(@PathVariable("food_id") int food_id, @RequestBody PetFood petFood) throws PetFoodIdNotFoundException, MismatchDataTypeException
	{
		return new ResponseEntity<>(petFoodService.updatePetFood(food_id, petFood), HttpStatus.OK);
	}
	
	@PutMapping("/quantity/{food_id}")
	public ResponseEntity<SuccessResponse> updatePetFoodQuantity(@PathVariable("food_id") int food_id, @RequestBody PetFood petFood) throws PetFoodQuantityNotFoundException
	{
		return new ResponseEntity<>(petFoodService.updatePetFoodQuantity(food_id, petFood), HttpStatus.OK);
	}

}
