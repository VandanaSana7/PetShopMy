package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.petshop.in.enums.TransactionStatus;
import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.transactions.FailedTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.SuccessTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionCannotBeAddedException;
import com.petshop.in.exceptions.transactions.TransactionIdNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionsNotFoundException;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.TransactionsRepository;
import com.petshop.in.service.TransactionsService;

@Service
public class TransactionsServiceImpl implements TransactionsService{

	@Autowired
	TransactionsRepository transactionRepo;
	
	public TransactionsServiceImpl(TransactionsRepository transactionRepository) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Transactions> getAllTransactions() throws TransactionsNotFoundException{
		
		List<Transactions> transactions = transactionRepo.findAll();
		if(transactions.isEmpty())
		{
			throw new TransactionsNotFoundException("Validation failed: No transactions found");
		}
		return transactions;
	}

	@Override
	public Transactions getTransactionsByTransactionId(int transaction_id) throws TransactionIdNotFoundException {
		
		try
		{
			Transactions transactions = transactionRepo.findById(transaction_id).get();
			return transactions;
		}
		catch(NoSuchElementException e)
		{
			throw new TransactionIdNotFoundException("Validation failed: No transaction Id found"); 
		}
		
	}

	@Override
	public Transactions getTransactionsByCustomerId(int customer_id) throws CustomerIdNotFoundException{
		
		try
		{
			Transactions transactions = transactionRepo.findById(customer_id).get();
			return transactions;
		}
		catch(NoSuchElementException e)
		{
			 throw new CustomerIdNotFoundException("Validation failed: No customer Id found");
		}
		
	}

	@Override
	public List<Transactions> getAllSuccessfulTransactions() throws SuccessTransactionNotFoundException, TransactionsNotFoundException{
		
		List<Transactions> successfulTransactions = new ArrayList<>();
		for(Transactions transaction : getAllTransactions())
		{
			if(transaction.getTransactionStatus() == TransactionStatus.Success)
			{
				successfulTransactions.add(transaction);
			}
		}
		if(successfulTransactions.isEmpty())
		{
			throw new SuccessTransactionNotFoundException("Validation failed: No transactions success");
		}
		return successfulTransactions;
	}

	@Override
	public List<Transactions> getAllFailedTransactions() throws FailedTransactionNotFoundException, TransactionsNotFoundException {
		
		List<Transactions> cancelledTransactions = new ArrayList<>();
		for(Transactions transaction : getAllTransactions())
		{
			if(transaction.getTransactionStatus() == TransactionStatus.Failed)
			{
				cancelledTransactions.add(transaction);
			}
		}
		if(cancelledTransactions.isEmpty())
		{
			throw new FailedTransactionNotFoundException("Validation failed: No transactions failed");
		}
		return cancelledTransactions;
	}

	@Override
	public SuccessResponse addTransactions(Transactions transaction) throws TransactionCannotBeAddedException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.isValidDateFormat(transaction.getTransactionDate()))
			{
				throw new MismatchDataTypeException("Invalid status format");
			}
			if(ValidationClass.validateTransactionStatus(transaction.getTransactionStatus()))
			{
				throw new MismatchDataTypeException("Invalid date format");
			}
			transactionRepo.save(transaction);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Transaction added successfully");
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(Exception e)
		{
			throw new TransactionCannotBeAddedException("Validation failed: Transaction cannot be added");
		}
	}

}
