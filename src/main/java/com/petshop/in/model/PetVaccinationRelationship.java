package com.petshop.in.model;

import com.petshop.in.dto.PetVaccinationRelationshipId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_vaccination_relationship")
public class PetVaccinationRelationship {

	@EmbeddedId
	private PetVaccinationRelationshipId id;
	
	@ManyToOne
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
	private Pets pet;
	
	@ManyToOne
	@JoinColumn(name = "vaccination_id", referencedColumnName = "vaccination_id", insertable = false, updatable = false)
	private Vaccinations vaccination;

	public Pets getPet() {
		return pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public Vaccinations getVaccination() {
		return vaccination;
	}

	public void setVaccination(Vaccinations vaccination) {
		this.vaccination = vaccination;
	}

	public PetVaccinationRelationshipId getId() {
		return id;
	}

	public void setId(PetVaccinationRelationshipId id) {
		this.id = id;
	}
	
	

//	public PetVaccinationRelationship(Pets pet, Vaccinations vaccination) {
//		super();
//		this.pet = pet;
//		this.vaccination = vaccination;
//	}
//
//	public PetVaccinationRelationship() {
//		super();
//	}
//
//	@Override
//	public String toString() {
//		return "PetVaccinationRelationship [pet=" + pet + ", vaccination=" + vaccination + "]";
//	}
	
	
}
