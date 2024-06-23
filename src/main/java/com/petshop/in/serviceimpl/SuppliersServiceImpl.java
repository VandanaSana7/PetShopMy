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
import com.petshop.in.exceptions.suppliers.SupplierCityNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.suppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierStateNotFoundException;
import com.petshop.in.exceptions.suppliers.SuppliersNotFoundException;
import com.petshop.in.model.Suppliers;
import com.petshop.in.repository.SuppliersRepository;
import com.petshop.in.service.SuppliersService;

@Service
public class SuppliersServiceImpl implements SuppliersService {

	@Autowired
	SuppliersRepository suppliersRepo;
	
	@Override
	public List<Suppliers> getAllSuppliers() throws SuppliersNotFoundException {
		
		List<Suppliers> suppliers = suppliersRepo.findAll();
		if(suppliers.isEmpty())
		{
			throw new SuppliersNotFoundException("Validation failed: No suppliers found");
		}
		return suppliers;
	}

	@Override
	public Suppliers getSuppliersBySupplierId(int supplier_id) throws SupplierIdNotFoundException{
		
		try
		{
			Suppliers suppliers = suppliersRepo.findById(supplier_id).get();
			return suppliers;
		}
		catch(NoSuchElementException e)
		{
			throw new SupplierIdNotFoundException("Validation failed: No supplier Id found");
		}
		
	}

	@Override
	public List<Suppliers> getSuppliersByName(String name) throws SupplierNameNotFoundException {
		
		List<Suppliers> suppliers = suppliersRepo.findByName(name);
		if(suppliers.isEmpty())
		{
			throw new SupplierNameNotFoundException("Validation failed: No supplier name found");
		}
		return suppliers;
	}

	@Override
	public List<Suppliers> getSuppliersByCityName(String city) throws SupplierCityNotFoundException {
		
		List<Suppliers> suppliers = suppliersRepo.findByCityName(city);
		if(suppliers.isEmpty())
		{
			throw new SupplierCityNotFoundException("Validation failed: No supplier city found");
		}
		return suppliers;
	}

	@Override
	public List<Suppliers> getSuppliersByStateName(String state) throws SupplierStateNotFoundException {
		
		List<Suppliers> suppliers = suppliersRepo.findByStateName(state);
		if(suppliers.isEmpty())
		{
			throw new SupplierStateNotFoundException("Validation failed: No supplier state found");
		}
		return suppliers;
	}

	@Override
	public SuccessResponse addSuppliers(Suppliers supplier) throws SupplierInputInvalidException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(supplier.getName()))
			{
				throw new MismatchDataTypeException("Name should be string");
			}
			if(!ValidationClass.validationInt(supplier.getContactPerson()))
			{
				throw new MismatchDataTypeException("Contact person should be string");
			}
			if(!ValidationClass.validationInt(supplier.getEmail()))
			{
				throw new MismatchDataTypeException("Email should be string");
			}
			suppliersRepo.save(supplier);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Supplier added successfully");
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(Exception e)
		{
			throw new SupplierInputInvalidException("Validation failed: Supplier cannot be added");
		}
	}

	@Override
	public SuccessResponse updateSuppliersBySupplierId(int supplier_id, Suppliers supplier) throws SupplierIdNotFoundException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(supplier.getName()))
			{
				throw new MismatchDataTypeException("Name should be string");
			}
			if(!ValidationClass.validationInt(supplier.getContactPerson()))
			{
				throw new MismatchDataTypeException("Contact person should be string");
			}
			if(!ValidationClass.validationInt(supplier.getEmail()))
			{
				throw new MismatchDataTypeException("Email should be string");
			}
			if(supplier.getName().isEmpty())
			{
				throw new MismatchDataTypeException("Name should not be null");
			}
			if(supplier.getPhoneNumber().isEmpty())
			{
				throw new MismatchDataTypeException("PhoneNumber should not be null");
			}
			if(supplier.getEmail().isEmpty())
			{
				throw new MismatchDataTypeException("Email should not be null");
			}
			Suppliers existingSupplier = suppliersRepo.findById(supplier_id).get();
			if(existingSupplier != null)
			{
				existingSupplier.setName(supplier.getName());
				existingSupplier.setAddress(supplier.getAddress());
				existingSupplier.setContactPerson(supplier.getContactPerson());
				existingSupplier.setEmail(supplier.getEmail());
				existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
			}
			suppliersRepo.save(existingSupplier);
			
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Supplier updated successfully");
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new SupplierIdNotFoundException("Validation failed: Supplier Id not found");
		}
		 
	}

}
