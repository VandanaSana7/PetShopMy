package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.petshop.in.enums.TransactionStatus;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.addresses.AddressInputInvalidException;
import com.petshop.in.exceptions.customers.CustomerCannotBeAddedException;
import com.petshop.in.exceptions.customers.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customers.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customers.CustomerFirstLastNameNotFoundException;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customers.CustomerNotFoundException;
import com.petshop.in.exceptions.customers.CustomerStateNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.model.Customers;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.CustomersRepository;
import com.petshop.in.repository.PetsRepository;
import com.petshop.in.repository.TransactionsRepository;
import com.petshop.in.service.CustomersService;

@Service
public class CustomersServiceImpl implements CustomersService{

	@Autowired
	CustomersRepository customerRepo;
	
	@Autowired
	TransactionsRepository transactionRepo;
	
	@Autowired
	PetsRepository petRepo;
	
	@Override
	public List<Customers> getAllCustomers() throws CustomerNotFoundException {
		
		List<Customers> customers = customerRepo.findAll();
		if(customers.isEmpty())
		{
			throw new CustomerNotFoundException("Validation failed: customers not found");
		}
		return customers;
	}

	@Override
	public Customers getCustomerById(int customer_id) throws CustomerIdNotFoundException{
		
		try
		{
			Customers customers = customerRepo.findById(customer_id).get();
			return customers;
		}
		catch(NoSuchElementException e)
		{
			throw new CustomerIdNotFoundException("Validation failed: No customer found with this Id "+customer_id);
		}
	}

	@Override
	public List<Customers> getCustomerByFirstAndLastName(String first_name, String last_name) throws CustomerFirstLastNameNotFoundException {
		
		List<Customers> customers = customerRepo.findByFirstAndLastName(first_name, last_name);
		if(customers.isEmpty())
		{
			throw new CustomerFirstLastNameNotFoundException("Validation failed: No customer with this name is found");
		}
		return customers;
	}

	@Override
	public List<Customers> getCustomersByCity(String city) throws CustomerCityNotFoundException {
		
		List<Customers> customers = customerRepo.findByCity(city);
		if(customers.isEmpty())
		{
			throw new CustomerCityNotFoundException("Validation failed: No customer with this city found");
		}
		return customers;
	}

	@Override
	public List<Customers> getCustomersByState(String state) throws CustomerStateNotFoundException {
		
		List<Customers> customers = customerRepo.findByState(state);
		if(customers.isEmpty())
		{
			throw new CustomerStateNotFoundException("Validation failed: No customer with this state found");
		}
		return customers;
	}

//	@Override
//	public List<Transactions> getAllCustomersTransactionHistory(int customer_id) {
//		
//		List<Transactions> customers = transactionRepo.findByTransactionHistory(customer_id);
//		return customers;
//	}
//
//	@Override
//	public List<Transactions> getAllCustomersTransactionStatus(TransactionStatus status) {
//		
//		List<Transactions> customers = transactionRepo.findByTransactionStatus(status);
//		return customers;
//	}
//
//	@Override
//	public List<Customers> getCustomersByNoTransactions() {
//		
//		return customerRepo.findCustomerByNotransaction();
//	}

//	@Override
//	public List<Pets> getAllPets(int customer_id) {
//		
//		List<Transactions> transactions = transactionRepo.findByTransactionHistory(customer_id);
//		Iterator i = transactions.listIterator();
//		List<Pets> pet = null;
//		Pets pets = null;
//		while(i.hasNext())
//		{
//			Transactions t = (Transactions)i.next();
//			int petId = t.getPet().getPetId();
//			pets = petRepo.findById(petId).get();
//		}
//		pet.add(pets);
//		return pet;
//	}
	
//	@Override
//	public Optional<Pets> getAllPets(int customer_id) {
//		
//		List<Transactions> transactions = transactionRepo.findByTransactionHistory(customer_id);
//		Iterator i = transactions.listIterator();
//
//		Optional<Pets> pets = java.util.Optional.empty();
//		while(i.hasNext())
//		{
//			Transactions t = (Transactions)i.next();
//			int petId = t.getPet().getPetId();
//			pets = petRepo.findById(petId);
//		}
//		
//		return pets;
//	}

	@Override
	public SuccessResponse addCustomers(Customers customer) throws CustomerCannotBeAddedException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(customer.getFirstName()))
			{
				throw new MismatchDataTypeException("First name should be string");
			}
			if(!ValidationClass.validationInt(customer.getLastName()))
			{
				throw new MismatchDataTypeException("Last name should be string");
			}
			if(!ValidationClass.validationInt(customer.getPhoneNumber()))
			{
				throw new MismatchDataTypeException("Phone number should be string");
			}
			Customers cust = customerRepo.save(customer);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Customer added successfully\n"+"\n"+cust);;
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new CustomerCannotBeAddedException("Validation failed: Customer is not valid");
		}
	}

	@Override
	public SuccessResponse updateCustomersByCustomerId(int customer_id, Customers customer) throws CustomerCannotBeUpdatedException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(customer.getFirstName()))
			{
				throw new MismatchDataTypeException("First name should be string");
			}
			if(!ValidationClass.validationInt(customer.getLastName()))
			{
				throw new MismatchDataTypeException("Last name should be string");
			}
			if(!ValidationClass.validationInt(customer.getPhoneNumber()))
			{
				throw new MismatchDataTypeException("Phone number should be string");
			}
			Customers existingCustomer = customerRepo.findById(customer_id).get();
			if(existingCustomer != null)
			{
				existingCustomer.setFirstName(customer.getFirstName());
				existingCustomer.setLastName(customer.getLastName());
				existingCustomer.setPhoneNumber(customer.getPhoneNumber());
				existingCustomer.setEmail(customer.getEmail());
				existingCustomer.setAddress(customer.getAddress());
			}
			customerRepo.save(existingCustomer);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Customer updated successfully\n"+existingCustomer);;
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new CustomerCannotBeUpdatedException("Validation failed: Customer is not valid");
		} 
	}

}
