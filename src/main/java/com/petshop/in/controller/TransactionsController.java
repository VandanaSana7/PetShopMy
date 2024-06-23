package com.petshop.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.transactions.FailedTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.SuccessTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionCannotBeAddedException;
import com.petshop.in.exceptions.transactions.TransactionIdNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionsNotFoundException;
import com.petshop.in.model.Transactions;
import com.petshop.in.serviceimpl.TransactionsServiceImpl;

@RestController
@RequestMapping("/api/v1/transaction_history")
public class TransactionsController {

	@Autowired
	TransactionsServiceImpl transactionService;
	
	@GetMapping
	public ResponseEntity<List<Transactions>> getAllTransactions() throws TransactionsNotFoundException
	{
		return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
	}
	
	@GetMapping("/{transaction_id}")
	public ResponseEntity<Transactions> getTransactionsByTransactionId(@PathVariable("transaction_id") int transaction_id) throws TransactionIdNotFoundException
	{
		return new ResponseEntity<>(transactionService.getTransactionsByTransactionId(transaction_id), HttpStatus.OK);
	}
	
	@GetMapping("/by_customer/{customer_id}")
	public ResponseEntity<Transactions> getTransactionsByCustomerId(@PathVariable("customer_id") int customer_id) throws CustomerIdNotFoundException
	{
		return new ResponseEntity<>(transactionService.getTransactionsByCustomerId(customer_id), HttpStatus.OK);
	}
	
	@GetMapping("/successful")
	public ResponseEntity<List<Transactions>> getAllSuccessfulTransactions() throws SuccessTransactionNotFoundException, TransactionsNotFoundException
	{
		return new ResponseEntity<>(transactionService.getAllSuccessfulTransactions(), HttpStatus.OK);
	}
	
	@GetMapping("/failed")
	public ResponseEntity<List<Transactions>> getAllFailedTransactions() throws FailedTransactionNotFoundException, TransactionsNotFoundException
	{
		return new ResponseEntity<>(transactionService.getAllFailedTransactions(), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> getAllSuccessfulTransactions(@RequestBody Transactions transaction) throws TransactionCannotBeAddedException, MismatchDataTypeException
	{
		return new ResponseEntity<>(transactionService.addTransactions(transaction), HttpStatus.OK);
	}
	
}
