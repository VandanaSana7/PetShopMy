package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.PetCategories;

import jakarta.transaction.Transactional;

@Repository
public interface PetCategoriesRepository extends JpaRepository<PetCategories, Integer>{
	
	@Transactional
	@Modifying
	@Query("UPDATE PetCategories p SET p.name = :categoryName WHERE p.id = :categoryId")
	void updateCategoryNameById(int categoryId, String categoryName);
	
	List<PetCategories> findByName(String name);

}
