package com.petshop.in.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer>{

	Employees findByFirstNameAndLastName(String first_name, String last_name);

	List<Employees> findByPosition(String position_name);

//	Employees findByFirstName(String first_name);
//
//	Optional<Employees> findByLastName(String last_name);

}
