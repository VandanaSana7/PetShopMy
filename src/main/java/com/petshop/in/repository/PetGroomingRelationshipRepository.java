package com.petshop.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.dto.PetGroomingRelationshipId;
import com.petshop.in.model.PetGroomingRelationship;

@Repository
public interface PetGroomingRelationshipRepository extends JpaRepository<PetGroomingRelationship, PetGroomingRelationshipId>{

}
