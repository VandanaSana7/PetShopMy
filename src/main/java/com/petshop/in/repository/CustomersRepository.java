package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petshop.in.enums.TransactionStatus;
import com.petshop.in.model.Customers;
import com.petshop.in.model.Transactions;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer>{

	@Query("SELECT c FROM Customers c WHERE c.firstName = :first_name AND c.lastName = :last_name")
	List<Customers> findByFirstAndLastName(String first_name, String last_name);

	@Query("SELECT c FROM Customers c JOIN c.address address WHERE address.city = :city")
	List<Customers> findByCity(String city);

	@Query("SELECT c FROM Customers c JOIN c.address address WHERE address.state = :state")
	List<Customers> findByState(String state);

//	@Query("SELECT c FROM Customers c WHERE c NOT IN (SELECT t.customer FROM Transactions t)")
//	List<Customers> findCustomerByNotransaction();
//
//	@Query("SELECT t FROM Transactions t WHERE t.customer.customerId = :customer_id")
//	List<Transactions> findByCustomerId(@Param("customer_id") int customer_id);
//
//	@Query("SELECT DISTINCT t.customer FROM Transactions t WHERE t.transactionStatus = :status")
//    List<Transactions> findCustomersByTransactionStatus(TransactionStatus status);

	

}
