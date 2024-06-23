package com.petshop.in.exceptions.transactions;

public class FailedTransactionNotFoundException extends Throwable {

	public FailedTransactionNotFoundException(String message)
	{
		super(message);
	}
	
}
