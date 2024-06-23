package com.petshop.in.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.model.Transactions;
import com.petshop.in.repository.TransactionsRepository;
import com.petshop.in.serviceimpl.TransactionsServiceImpl;



@SpringBootTest
public class TransactionServiceImplTest {

    private TransactionsServiceImpl transactionService;
    private TransactionsRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionsRepository.class);
        transactionService = new TransactionsServiceImpl(transactionRepository);
    }


    @Test
    void testRetrieveCustById() {
        Transactions transaction = new Transactions();
        int customerId = 1;
        when(transactionRepository.findById(customerId)).thenReturn(Optional.of(transaction));

       
    }
//  @Test
//    void testAddTransaction() {
//        Transactions transaction = new Transactions();
//        when(transactionRepository.save(transaction)).thenReturn(transaction);
//
//        assertThrows(MismatchDataTypeException.class, () -> {
//            transactionService.addTransactions(transaction)
//        });
//  }
  

 
  }
  
      
  


