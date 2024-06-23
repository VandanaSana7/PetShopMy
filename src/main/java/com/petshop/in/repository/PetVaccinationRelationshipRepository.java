package com.petshop.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.dto.PetVaccinationRelationshipId;
import com.petshop.in.model.PetVaccinationRelationship;

@Repository
public interface PetVaccinationRelationshipRepository extends JpaRepository<PetVaccinationRelationship, PetVaccinationRelationshipId>{

}
