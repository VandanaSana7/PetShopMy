package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.pets.NoFoodInfoFoundException;
import com.petshop.in.exceptions.pets.NoGroomingServicesFoundException;
import com.petshop.in.exceptions.pets.NoPetCategoryFoundException;
import com.petshop.in.exceptions.pets.NoSuppliersFoundException;
import com.petshop.in.exceptions.pets.NoTransactionsFoundException;
import com.petshop.in.exceptions.pets.NoVaccinationsFoundException;
import com.petshop.in.exceptions.pets.PetCannotBeAddedException;
import com.petshop.in.exceptions.pets.PetIdNotFoundException;
import com.petshop.in.exceptions.pets.PetsNotFoundException;
import com.petshop.in.model.PetGroomingRelationship;
import com.petshop.in.model.PetVaccinationRelationship;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.PetCategoriesRepository;
import com.petshop.in.repository.PetFoodRelationshipRepository;
import com.petshop.in.repository.PetGroomingRelationshipRepository;
import com.petshop.in.repository.PetSupplierRelationshipRepository;
import com.petshop.in.repository.PetVaccinationRelationshipRepository;
import com.petshop.in.repository.PetsRepository;
import com.petshop.in.repository.TransactionsRepository;
import com.petshop.in.service.PetService;

@Service
public class PetServiceImpl implements PetService{

	@Autowired
	private PetsRepository petsRepo;
	
	@Autowired
	private PetGroomingRelationshipRepository petGroomingRepo;
	
	@Autowired
	private PetVaccinationRelationshipRepository petVaccinationRepo;
	
	@Autowired
	private PetFoodRelationshipRepository petFoodRepo;
	
	@Autowired
	private PetSupplierRelationshipRepository petSupplierRepo;
	
	@Autowired
	private TransactionsRepository transactionsRepo;
	
	@Autowired
	private PetCategoriesRepository petCategoriesRepo;
	
	public PetServiceImpl() {
		super();
		
	}

	public PetServiceImpl(PetsRepository petsRepo, PetGroomingRelationshipRepository petGroomingRepo,
			PetVaccinationRelationshipRepository petVaccinationRepo, PetFoodRelationshipRepository petFoodrepo,
			PetSupplierRelationshipRepository petSupplierRepo, TransactionsRepository transactionsRepo,
			PetCategoriesRepository petCategoriesRepo) {
		super();
		this.petsRepo = petsRepo;
		this.petGroomingRepo = petGroomingRepo;
		this.petVaccinationRepo = petVaccinationRepo;
		this.petFoodRepo = petFoodrepo;
		this.petSupplierRepo = petSupplierRepo;
		this.transactionsRepo = transactionsRepo;
		this.petCategoriesRepo = petCategoriesRepo;
	}

	@Override
	public List<Pets> getAllPets() throws PetsNotFoundException {
		
		List<Pets> pets = petsRepo.findAll();
		if(pets.isEmpty())
		{
			throw new PetsNotFoundException("Validation failed: No pets are available in the pet shop");
		}
		return pets;
	}

	@Override
	public Pets getPetsById(int pet_id) throws PetIdNotFoundException {
		
		try
		{
			Pets pet = petsRepo.findById(pet_id).get();
			return pet;
		}
		catch(NoSuchElementException e)
		{
			throw new PetIdNotFoundException("Validation failed: No pet found with the Id "+pet_id);
		}
		
	}

	@Override
	public List<Pets> getPetsByCategory(String category) throws NoPetCategoryFoundException {
		List<Pets> petsByCategory = petsRepo.findByPetCategoryName(category);
		if(petsByCategory.isEmpty())
		{
			throw new NoPetCategoryFoundException("Validation failed: No pets with category name are available");
		}
		return petsByCategory;
	}

	@Override
	public List<Object> getPetsByGroomingServices(int pet_id) throws NoGroomingServicesFoundException, PetIdNotFoundException {
		
		try
		{
			List<Object> petsByGrooming = new LinkedList<>();
			List<PetGroomingRelationship> list_pgr = petGroomingRepo.findAll();
			Pets pet = petsRepo.findById(pet_id).get();
			ListIterator<PetGroomingRelationship> iterator_pgr = list_pgr.listIterator();
			while(iterator_pgr.hasNext())
			{
				PetGroomingRelationship pgr = iterator_pgr.next();
				if(pet.getPetId() == pgr.getPet().getPetId())
				{
					petsByGrooming.add(pgr.getPet());
					petsByGrooming.add(pgr.getGroomingService());
				}
			}
			if(petsByGrooming.isEmpty())
			{
				throw new NoGroomingServicesFoundException("Validation failed: No grooming services available for this pet");
			}
			return petsByGrooming;
		}
		catch(NoSuchElementException e)
		{
			throw new PetIdNotFoundException("Validation failed: No pet found with the Id");
		}
		
	}

	@Override
	public List<Object> getPetsByVaccinations(int pet_id) throws NoVaccinationsFoundException, PetIdNotFoundException {
		
		try
		{
			List<Object> petsByVaccination = new LinkedList<>();
			List<PetVaccinationRelationship> list_pvr = petVaccinationRepo.findAll();
			Pets pet = petsRepo.findById(pet_id).get();
			ListIterator<PetVaccinationRelationship> iterator_pvr = list_pvr.listIterator();
			while(iterator_pvr.hasNext())
			{
				PetVaccinationRelationship pvr = iterator_pvr.next();
				if(pet.getPetId() == pvr.getPet().getPetId())
				{
					petsByVaccination.add(pvr.getPet());
					petsByVaccination.add(pvr.getVaccination());
				}
			}
			if(petsByVaccination.isEmpty())
			{
				throw new NoVaccinationsFoundException("Validation failed: No vaccinations found for the pet");
			}
			return petsByVaccination;
		}
		catch(NoSuchElementException e)
		{
			throw new PetIdNotFoundException("Validation failed: No pet found with this Id");
		}
		
	}

	@Override
	public List<Object[]> getPetsByPetFood(int pet_id) throws NoFoodInfoFoundException, PetIdNotFoundException {
		
		List<Object[]> list = petFoodRepo.findFoodDetailsByPetId(pet_id);
		try
		{
			if(list.isEmpty())
			{
				throw new NoFoodInfoFoundException("Validation failed: No pet food info found for the pet");
			}
		}
		catch(NoSuchElementException e)
		{
			throw new PetIdNotFoundException("Validation failed: No pet found with this Id");
		}
		return list;
	}

	@Override
	public List<Object[]> getSupplierByPetId(int pet_id) throws NoSuppliersFoundException, PetIdNotFoundException {
		
		List<Object[]> list = petSupplierRepo.findSupplierByPetId(pet_id);
		try
		{
			if(list.isEmpty())
			{
				throw new NoSuppliersFoundException("Validation failed: No suppliers found for the pet");
			}
		}
		catch(NoSuchElementException e)
		{
			throw new PetIdNotFoundException("Validation failed: No pet found with this Id");
		}
		return list;
	}

	@Override
	public Transactions getTransactionByPetId(int pet_id) throws NoTransactionsFoundException, PetIdNotFoundException {
		
		try
		{
			List<Transactions> list = transactionsRepo.findAll();
			Transactions t = null;
			Iterator<Transactions> iterator = list.listIterator();
			while(iterator.hasNext())
			{
				Transactions t1 = (Transactions)iterator.next();
				if(t1.getPet().getPetId() == pet_id)
				{
					t = t1;
				}
			}
			if(t == null)
			{
				throw new NoTransactionsFoundException("Validation failed: No transactions found for the given pet");
			}
			return t;
		}
		catch(NoSuchElementException e)
		{
			throw new PetIdNotFoundException("Validation failed: No pet found with this Id");
		}
		
	}

	@Override
	public SuccessResponse addPets(Pets pet) throws PetCannotBeAddedException, MismatchDataTypeException{
		
		try
		{
			if(ValidationClass.validationInt(pet.getName()) == false)
			{
				throw new MismatchDataTypeException("Name should be string");
			}
			if(ValidationClass.validationInt(pet.getBreed()) == false)
			{
				throw new MismatchDataTypeException("Breed should be string");
			}
			if(ValidationClass.validationInt(pet.getDescription()) == false)
			{
				throw new MismatchDataTypeException("Description should be text");
			}
			if(ValidationClass.validationInt(pet.getImageUrl()) == false)
			{
				throw new MismatchDataTypeException("Url should be string");
			}
			
			List<Pets> petNames = petsRepo.findByPetCategoryName(pet.getPetCategory().getName());
			if(petNames.isEmpty())
			{
				petCategoriesRepo.save(pet.getPetCategory());
			}
			
			Pets p = petsRepo.save(pet);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Data added "+p);
			s.setStatus("Success");
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw new PetCannotBeAddedException("Validation failed: Pet cannot be added " + e.getMessage());
		}
		
	}

	@Override
	public SuccessResponse updatePetsByPetId(Pets pet, int pet_id) throws PetIdNotFoundException, MismatchDataTypeException{
		
		try
		{
			Pets p = petsRepo.findById(pet_id).get();
			if(p != null)
			{
				if(ValidationClass.validationInt(pet.getName()) == false)
				{
					throw new MismatchDataTypeException("Name should be string");
				}
				if(ValidationClass.validationInt(pet.getBreed()) == false)
				{
					throw new MismatchDataTypeException("Breed should be string");
				}
				if(ValidationClass.validationInt(pet.getDescription()) == false)
				{
					throw new MismatchDataTypeException("Description should be text");
				}
				if(ValidationClass.validationInt(pet.getImageUrl()) == false)
				{
					throw new MismatchDataTypeException("Url should be string");
				}
				p.setAge(pet.getAge());
				p.getPetCategory().setName(pet.getPetCategory().getName());
				p.setBreed(pet.getBreed());
				p.setDescription(pet.getDescription());
				p.setImageUrl(pet.getImageUrl());
				p.setName(pet.getName());
				p.setPrice(pet.getPrice());
			}
			petsRepo.save(p);
			
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Data updated\n" + p);
			s.setStatus("Success");
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			System.out.println(e.getMessage());
			throw new PetIdNotFoundException("Validation failed: Pet Id not found " + e.getMessage());
		}
		
		
		
	}

}
