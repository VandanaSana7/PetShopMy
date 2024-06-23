package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.petfood.PetFoodAddException;
import com.petshop.in.exceptions.petfood.PetFoodBrandNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodIdNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodNameNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodQuantityNotFoundException;
import com.petshop.in.exceptions.petfood.PetFoodTypeNotFoundException;
import com.petshop.in.model.PetFood;
import com.petshop.in.repository.PetFoodRepository;
import com.petshop.in.service.PetFoodService;

@Service
public class PetFoodServiceImpl implements PetFoodService{

	@Autowired
	PetFoodRepository petFoodRepo;
	
	@Override
	public List<PetFood> getAllPetFood() throws PetFoodNotFoundException{
		
		List<PetFood> petFood = petFoodRepo.findAll();
		if(petFood.isEmpty())
		{
			throw new PetFoodNotFoundException("Validation failed: No pet food found");
		}
		return petFood;
	}

	@Override
	public PetFood getPetFoodByFoodId(int food_id) throws PetFoodIdNotFoundException {
		
		try
		{
			PetFood petFood = petFoodRepo.findById(food_id).get();
			return petFood;
		}
		catch(NoSuchElementException e)
		{
			throw new PetFoodIdNotFoundException("Validation failed: Food Id not found");
		}
		
	}

	@Override
	public List<PetFood> getPetFoodByFoodName(String food_name) throws PetFoodNameNotFoundException {
		
		List<PetFood> petFood = petFoodRepo.findByName(food_name);
		if(petFood.isEmpty())
		{
			throw new PetFoodNameNotFoundException("Validation failed: Food name not found");
		}
		return petFood;
	}

	@Override
	public List<PetFood> getPetFoodByFoodType(String food_type) throws PetFoodTypeNotFoundException{
		
		List<PetFood> petFood = petFoodRepo.findByType(food_type);
		if(petFood.isEmpty())
		{
			throw new PetFoodTypeNotFoundException("Validation failed: Food type not found");
		}
		return petFood;
	}

	@Override
	public List<PetFood> getPetFoodByBrandName(String brand_name) throws PetFoodBrandNotFoundException{
		
		List<PetFood> petFood = petFoodRepo.findByBrand(brand_name);
		if(petFood.isEmpty())
		{
			throw new PetFoodBrandNotFoundException("Validation failed: Food brand not found");
		}
		return petFood;
	}

	@Override
	public SuccessResponse addPetFood(PetFood petFood) throws PetFoodAddException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(petFood.getName()))
			{
				throw new MismatchDataTypeException("Name should be string");
			}
			if(!ValidationClass.validationInt(petFood.getBrand()))
			{
				throw new MismatchDataTypeException("Brand should be string");
			}
			if(!ValidationClass.validationInt(petFood.getType()))
			{
				throw new MismatchDataTypeException("Type should be string");
			}
			petFoodRepo.save(petFood);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Petfood added successfully");
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(Exception e)
		{
			throw new PetFoodAddException("Validation failed: Food cannot be added");
		}
		
	}

	@Override
	public SuccessResponse updatePetFood(int food_id, PetFood petFood) throws PetFoodIdNotFoundException, MismatchDataTypeException {
		
		try
		{
			PetFood existingPetFood = petFoodRepo.findById(food_id).get();
			if(existingPetFood != null)
			{
				if(!ValidationClass.validationInt(petFood.getName()))
				{
					throw new MismatchDataTypeException("Name should be string");
				}
				if(!ValidationClass.validationInt(petFood.getBrand()))
				{
					throw new MismatchDataTypeException("Brand should be string");
				}
				if(!ValidationClass.validationInt(petFood.getType()))
				{
					throw new MismatchDataTypeException("Type should be string");
				}
				existingPetFood.setName(petFood.getName());
				existingPetFood.setBrand(petFood.getBrand());
				existingPetFood.setType(petFood.getType());
				existingPetFood.setQuantity(petFood.getQuantity());
				existingPetFood.setPrice(petFood.getPrice());
			}
			petFoodRepo.save(existingPetFood);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Petfood updated successfully");
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new PetFoodIdNotFoundException("Validation failed: Food cannot be updated");
		}
		
	}

	@Override
	public SuccessResponse updatePetFoodQuantity(int food_id, PetFood food) throws PetFoodQuantityNotFoundException {
		
		
		try
		{
			PetFood existingPetFood = petFoodRepo.findById(food_id).get();
			existingPetFood.setQuantity(food.getQuantity());
			
			petFoodRepo.save(existingPetFood);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Petfood quantity updated successfully");
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new PetFoodQuantityNotFoundException("Validation failed: Food quantity cannot be updated");
		}
		
	}

}
