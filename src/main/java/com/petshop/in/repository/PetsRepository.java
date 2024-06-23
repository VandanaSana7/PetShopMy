package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.Pets;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Integer>{

	List<Pets> findByPetCategoryName(String category);
	
}
