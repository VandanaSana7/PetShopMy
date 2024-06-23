package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.petcategories.CategoryAddException;
import com.petshop.in.exceptions.petcategories.CategoryIdNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNameNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNotFoundException;
import com.petshop.in.model.PetCategories;
import com.petshop.in.repository.PetCategoriesRepository;
import com.petshop.in.service.PetCategoriesService;

@Service
public class PetCategoriesServiceImpl implements PetCategoriesService{

	@Autowired
	PetCategoriesRepository petCategoriesRepo;
	
	@Override
	public List<PetCategories> getAllPetCategories() throws CategoryNotFoundException {
		
		List<PetCategories> pet_categories = petCategoriesRepo.findAll();
		if(pet_categories.isEmpty())
		{
			throw new CategoryNotFoundException("Validation failed: No Category found");
		}
		return pet_categories;
	}

	@Override
	public PetCategories getPetCategoriesByCategoryId(int category_id) throws CategoryIdNotFoundException {
		
		try
		{
			PetCategories pet_categories = petCategoriesRepo.findById(category_id).get();
			return pet_categories;
		}
		catch(NoSuchElementException e)
		{
			throw new CategoryIdNotFoundException("Validation failed: Category Id not found");
		}
		
	}

//	@Override
//	public PetCategories getPetCategoryByCategoryName(String category_name) {
//		
//		return null;
//	}
	
	@Override
	public List<PetCategories> getPetCategoryByCategoryName(String category_name) throws CategoryNameNotFoundException {
		
		try
		{
			List<PetCategories> pet_categories = petCategoriesRepo.findByName(category_name);
			return pet_categories;
		}
		catch(NoSuchElementException e)
		{
			throw new CategoryNameNotFoundException("Validation failed: Category name not found");
		}
		
	}


	@Override
	public SuccessResponse addCategory(PetCategories petCategory) throws MismatchDataTypeException, CategoryAddException{
		
		try
		{
			if(!ValidationClass.validationInt(petCategory.getName()))
			{
				throw new MismatchDataTypeException("Category name should be a string");
			}
			PetCategories category = petCategoriesRepo.save(petCategory);
			
			if(category == null)
			{
				throw new CategoryAddException("Failed to add category");
			}
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Data added successfully");
			s.setStatus("Success");
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(Exception e)
		{
			throw new CategoryAddException("Failed to add category");
		}
	}

	@Override
	public SuccessResponse updateCategoryByCategoryId(int category_id, PetCategories category_name) throws MismatchDataTypeException, CategoryIdNotFoundException{
		
		try
		{
			PetCategories existingCategory = petCategoriesRepo.findById(category_id).get();
			if(existingCategory != null)
			{
				if(!ValidationClass.validationInt(category_name.getName()))
				{
					throw new MismatchDataTypeException("Category name should be a non-empty string");
				}
				existingCategory.setName(category_name.getName());
				petCategoriesRepo.save(existingCategory);
				SuccessResponse s = new SuccessResponse();
				s.setMessage("Data updated successfully");
				s.setStatus("Success");
				s.setTimeStamp(LocalDate.now());
				return s;
			}
			else
			{
				throw new CategoryIdNotFoundException("Category Id is not valid");
			}
		}
		catch(NoSuchElementException e)
		{
			throw new CategoryIdNotFoundException("Category Id is not valid");
		}

	}
	

}
