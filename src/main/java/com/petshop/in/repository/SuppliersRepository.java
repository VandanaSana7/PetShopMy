package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer>{

	List<Suppliers> findByName(String name);

	@Query("SELECT s FROM Suppliers s JOIN s.address a WHERE a.city = :city")
	List<Suppliers> findByCityName(String city);
	
	@Query("SELECT s FROM Suppliers s JOIN s.address a WHERE a.state = :state")
	List<Suppliers> findByStateName(String state);

}
