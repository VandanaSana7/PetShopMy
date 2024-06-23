package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.petcategories.CategoryAddException;
import com.petshop.in.exceptions.petcategories.CategoryIdNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNameNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNotFoundException;
import com.petshop.in.model.PetCategories;

public interface PetCategoriesService {
	
	List<PetCategories> getAllPetCategories() throws CategoryNotFoundException;
	PetCategories getPetCategoriesByCategoryId(int category_id) throws CategoryIdNotFoundException;
	List<PetCategories> getPetCategoryByCategoryName(String category_name) throws CategoryNameNotFoundException;
	SuccessResponse addCategory(PetCategories petCategory) throws MismatchDataTypeException, CategoryAddException;
	SuccessResponse updateCategoryByCategoryId(int category_id, PetCategories category_name) throws MismatchDataTypeException, CategoryIdNotFoundException;
	
}
