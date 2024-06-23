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
import com.petshop.in.exceptions.petcategories.CategoryAddException;
import com.petshop.in.exceptions.petcategories.CategoryIdNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNameNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNotFoundException;
import com.petshop.in.model.PetCategories;
import com.petshop.in.serviceimpl.PetCategoriesServiceImpl;

@RestController
@RequestMapping("/api/v1/categories")
public class PetCategoriesController {
	
	@Autowired
	PetCategoriesServiceImpl petCategoriesService;
	
	@GetMapping
	public ResponseEntity<List<PetCategories>> getAllPetCategories() throws CategoryNotFoundException
	{
		return new ResponseEntity<>(petCategoriesService.getAllPetCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/{category_id}")
	public ResponseEntity<PetCategories> getAllPetCategoriesById(@PathVariable("category_id") int category_id) throws CategoryIdNotFoundException
	{
		return new ResponseEntity<>(petCategoriesService.getPetCategoriesByCategoryId(category_id), HttpStatus.OK);
	}
	
	@GetMapping("/name/{category_name}")
	public ResponseEntity<List<PetCategories>> getAllPetCategoriesByName(@PathVariable("category_name") String category_name) throws CategoryNameNotFoundException
	{
		return new ResponseEntity<>(petCategoriesService.getPetCategoryByCategoryName(category_name), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addPetCategories(@RequestBody PetCategories petCategory) throws MismatchDataTypeException, CategoryAddException
	{
		return new ResponseEntity<>(petCategoriesService.addCategory(petCategory), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{category_id}")
	public ResponseEntity<SuccessResponse> updatePetCategories(@PathVariable("category_id") int category_id, @RequestBody PetCategories petCategory) throws MismatchDataTypeException, CategoryIdNotFoundException
	{
		return new ResponseEntity<>(petCategoriesService.updateCategoryByCategoryId(category_id, petCategory), HttpStatus.OK);
	}
	
}
