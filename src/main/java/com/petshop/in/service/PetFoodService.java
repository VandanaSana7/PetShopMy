package com.petshop.in.service;

import java.util.List;

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

public interface PetFoodService {

	List<PetFood> getAllPetFood() throws PetFoodNotFoundException;
	PetFood getPetFoodByFoodId(int food_id) throws PetFoodIdNotFoundException;
	List<PetFood> getPetFoodByFoodName(String food_name) throws PetFoodNameNotFoundException;
	List<PetFood> getPetFoodByFoodType(String food_type) throws PetFoodTypeNotFoundException;
	List<PetFood> getPetFoodByBrandName(String brand_name) throws PetFoodBrandNotFoundException;
	SuccessResponse addPetFood(PetFood petFood) throws PetFoodAddException, MismatchDataTypeException;
	SuccessResponse updatePetFood(int food_id, PetFood petFood)  throws PetFoodIdNotFoundException, MismatchDataTypeException;
	SuccessResponse updatePetFoodQuantity(int food_id, PetFood food) throws PetFoodQuantityNotFoundException;
	
}
