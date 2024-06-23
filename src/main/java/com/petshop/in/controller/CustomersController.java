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
import com.petshop.in.serviceimpl.CustomersServiceImpl;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

	@Autowired
	CustomersServiceImpl customerService;
	
	@GetMapping
	public ResponseEntity<List<Customers>> getAllCustomers() throws CustomerNotFoundException
	{
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/{customer_id}")
	public ResponseEntity<Customers> getCustomerById(@PathVariable("customer_id") int customer_id) throws CustomerIdNotFoundException
	{
		return new ResponseEntity<>(customerService.getCustomerById(customer_id), HttpStatus.OK);
	}
	
	@GetMapping("/name/{first_name}/{last_name}")
	public ResponseEntity<List<Customers>> getCustomerByFirstAndLastName(@PathVariable("first_name") String first_name, @PathVariable("last_name") String last_name) throws CustomerFirstLastNameNotFoundException
	{
		return new ResponseEntity<>(customerService.getCustomerByFirstAndLastName(first_name, last_name), HttpStatus.OK);
	}
	
	@GetMapping("/by_city/{city}")
	public ResponseEntity<List<Customers>> getCustomerByCity(@PathVariable("city") String city) throws CustomerCityNotFoundException
	{
		return new ResponseEntity<>(customerService.getCustomersByCity(city), HttpStatus.OK);
	}
	
	@GetMapping("/by_state/{state}")
	public ResponseEntity<List<Customers>> getCustomerByState(@PathVariable("state") String state) throws CustomerStateNotFoundException
	{
		return new ResponseEntity<>(customerService.getCustomersByState(state), HttpStatus.OK);
	}
	
//	@GetMapping("/transactions/{customer_id}")
//	public ResponseEntity<List<Transactions>> getCustomertransactionHistory(@PathVariable("customer_id") int customer_id)
//	{
//		return new ResponseEntity<>(customerService.getAllCustomersTransactionHistory(customer_id), HttpStatus.OK);
//	}
//	
//	@GetMapping("/transaction_status/{status}")
//	public ResponseEntity<List<Transactions>> getCustomerTransactionStatus(@PathVariable("status") TransactionStatus status)
//	{
//		return new ResponseEntity<>(customerService.getAllCustomersTransactionStatus(status), HttpStatus.OK);
//	}
//	
//	@GetMapping("/no-transactions")
//	public ResponseEntity<List<Customers>> getCustomerByNoTransactions()
//	{
//		return new ResponseEntity<>(customerService.getCustomersByNoTransactions(), HttpStatus.OK);
//	}
	
//	@GetMapping("/pets/{customer_id}")
//	public ResponseEntity<List<Pets>> get(@PathVariable("customer_id") int customer_id)
//	{
//		return new ResponseEntity<>(customerService.getAllPets(customer_id), HttpStatus.OK);
//	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addCustomers(@RequestBody Customers customer) throws CustomerCannotBeAddedException, MismatchDataTypeException
	{
		return new ResponseEntity<>(customerService.addCustomers(customer), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{customer_id}")
	public ResponseEntity<SuccessResponse> updateCustomersByCustomerId(@PathVariable("customer_id") int customer_id, @RequestBody Customers customer) throws CustomerCannotBeUpdatedException, MismatchDataTypeException
	{
		return new ResponseEntity<>(customerService.updateCustomersByCustomerId(customer_id, customer), HttpStatus.CREATED);
	}
	
}
