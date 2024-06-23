package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.dto.PetFoodRelationshipId;
import com.petshop.in.model.PetFoodRelationship;

@Repository
public interface PetFoodRelationshipRepository extends JpaRepository<PetFoodRelationship, PetFoodRelationshipId>{

	@Query("SELECT pfr.pet, pfr.petFood FROM PetFoodRelationship pfr WHERE pfr.id.pet_id = :pet_id")
	List<Object[]> findFoodDetailsByPetId(int pet_id);
	
}
