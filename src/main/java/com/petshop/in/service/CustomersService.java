package com.petshop.in.service;

import java.util.List;

import com.petshop.in.enums.TransactionStatus;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.customers.CustomerCannotBeAddedException;
import com.petshop.in.exceptions.customers.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customers.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customers.CustomerFirstLastNameNotFoundException;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customers.CustomerNotFoundException;
import com.petshop.in.exceptions.customers.CustomerStateNotFoundException;
import com.petshop.in.model.Customers;
import com.petshop.in.model.Pets;
import com.petshop.in.model.Transactions;

public interface CustomersService {

	List<Customers> getAllCustomers() throws CustomerNotFoundException;
	Customers getCustomerById(int customer_id) throws CustomerIdNotFoundException;
	List<Customers> getCustomerByFirstAndLastName(String first_name, String last_name) throws CustomerFirstLastNameNotFoundException;
	List<Customers> getCustomersByCity(String city) throws CustomerCityNotFoundException;
	List<Customers> getCustomersByState(String state)throws CustomerStateNotFoundException;
//	List<Transactions> getAllCustomersTransactionHistory(int customer_id);
//	List<Transactions> getAllCustomersTransactionStatus(TransactionStatus status);
//	List<Customers> getCustomersByNoTransactions();
//	List<Pets> getAllPets(int customer_id);
	SuccessResponse addCustomers(Customers customer) throws CustomerCannotBeAddedException, MismatchDataTypeException ;
	SuccessResponse updateCustomersByCustomerId(int customer_id, Customers customer) throws CustomerCannotBeUpdatedException, MismatchDataTypeException ;
	
	
	// Optional<Pets> getAllPets(int customer_id);
}
