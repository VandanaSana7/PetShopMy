package com.petshop.in.model;

import com.petshop.in.dto.PetFoodRelationshipId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_food_relationship")
public class PetFoodRelationship {
	
	@EmbeddedId
	private PetFoodRelationshipId id;
	
	@ManyToOne
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
	private Pets pet;
	
	@ManyToOne
	@JoinColumn(name = "food_id", referencedColumnName = "food_id", insertable = false, updatable = false)
	private PetFood petFood;

	public Pets getPet() {
		return pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public PetFood getPetFood() {
		return petFood;
	}

	public void setPetFood(PetFood petFood) {
		this.petFood = petFood;
	}

	public PetFoodRelationshipId getId() {
		return id;
	}

	public void setId(PetFoodRelationshipId id) {
		this.id = id;
	}
	
	

//	public PetFoodRelationship(Pets pet, PetFood petFood) {
//		super();
//		this.pet = pet;
//		this.petFood = petFood;
//	}
//
//	public PetFoodRelationship() {
//		super();
//		
//	}
//
//	@Override
//	public String toString() {
//		return "PetFoodRelationship [pet=" + pet + ", petFood=" + petFood + "]";
//	}
	
	
	
}
