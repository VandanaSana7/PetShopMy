package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.dto.PetSupplierRelationshipId;
import com.petshop.in.model.PetSupplierRelationship;

@Repository
public interface PetSupplierRelationshipRepository extends JpaRepository<PetSupplierRelationship, PetSupplierRelationshipId>{
	
	@Query("SELECT psr.pet, psr.supplier FROM PetSupplierRelationship psr WHERE psr.id.pet_id = :pet_id")
	List<Object[]> findSupplierByPetId(int pet_id);	
}
