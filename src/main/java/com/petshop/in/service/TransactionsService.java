package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.transactions.FailedTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.SuccessTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionCannotBeAddedException;
import com.petshop.in.exceptions.transactions.TransactionIdNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionsNotFoundException;
import com.petshop.in.model.Transactions;

public interface TransactionsService {

	List<Transactions> getAllTransactions() throws TransactionsNotFoundException;
	Transactions getTransactionsByTransactionId(int transaction_id) throws TransactionIdNotFoundException ;
	Transactions getTransactionsByCustomerId(int customer_id) throws CustomerIdNotFoundException;
	List<Transactions> getAllSuccessfulTransactions() throws SuccessTransactionNotFoundException, TransactionsNotFoundException;
	List<Transactions> getAllFailedTransactions() throws FailedTransactionNotFoundException, TransactionsNotFoundException;
	SuccessResponse addTransactions(Transactions transaction) throws TransactionCannotBeAddedException, MismatchDataTypeException;
	
}
