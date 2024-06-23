package com.petshop.in.service;

import java.util.List;

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

public interface PetService {
	
	List<Pets> getAllPets() throws PetsNotFoundException;
	
	Pets getPetsById(int pet_id) throws PetIdNotFoundException;
	
	List<Pets> getPetsByCategory(String category) throws NoPetCategoryFoundException;
	
	List<Object> getPetsByGroomingServices(int pet_id) throws NoGroomingServicesFoundException, PetIdNotFoundException;
	
	List<Object> getPetsByVaccinations(int pet_id) throws NoVaccinationsFoundException, PetIdNotFoundException;
	
	List<Object[]> getPetsByPetFood(int pet_id) throws NoFoodInfoFoundException, PetIdNotFoundException;
	
	List<Object[]> getSupplierByPetId(int pet_id) throws NoSuppliersFoundException, PetIdNotFoundException;
	
	Transactions getTransactionByPetId(int pet_id) throws NoTransactionsFoundException, PetIdNotFoundException;
	
	SuccessResponse addPets(Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException;
	
	SuccessResponse updatePetsByPetId(Pets pet, int pet_id) throws PetIdNotFoundException, MismatchDataTypeException;
	
}
