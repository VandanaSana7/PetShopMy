package com.petshop.in.model;



import com.petshop.in.dto.PetGroomingRelationshipId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_grooming_relationship")
public class PetGroomingRelationship {
	
	@EmbeddedId
	private PetGroomingRelationshipId id;
	
	@ManyToOne
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
	private Pets pet;
	
	@ManyToOne
	@JoinColumn(name = "service_id", referencedColumnName = "service_id", insertable = false, updatable = false)
	private GroomingServices groomingService;

	public Pets getPet() {
		return pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public GroomingServices getGroomingService() {
		return groomingService;
	}

	public void setGroomingService(GroomingServices groomingService) {
		this.groomingService = groomingService;
	}

	public PetGroomingRelationshipId getId() {
		return id;
	}

	public void setId(PetGroomingRelationshipId id) {
		this.id = id;
	}
	
	

//	public PetGroomingRelationship(Pets pet, GroomingServices groomingService) {
//		super();
//		this.pet = pet;
//		this.groomingService = groomingService;
//	}
//
//	public PetGroomingRelationship() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
	@Override
	public String toString() {
		return "PetGroomingRelationship [pet=" + pet + ", groomingService=" + groomingService + "]";
	}
	
	
	
}
