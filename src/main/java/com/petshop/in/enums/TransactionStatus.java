package com.petshop.in.enums;

public enum TransactionStatus {
	Failed,
	Success;
	
	public String getStatus()
	{
		return this.name();
	}
}
