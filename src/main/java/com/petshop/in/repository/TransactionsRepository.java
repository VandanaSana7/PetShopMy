package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.enums.TransactionStatus;
import com.petshop.in.model.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer>{
//
//	List<Transactions> findByTransactionHistory(int customer_id);
//
//	List<Transactions> findByTransactionStatus(TransactionStatus status);
	
}
