package com.petshop.in.model;


import com.petshop.in.dto.PetSupplierRelationshipId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_supplier_relationship")
public class PetSupplierRelationship {
	
	@EmbeddedId
	private PetSupplierRelationshipId id;

	@ManyToOne
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
	private Pets pet;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id", insertable = false, updatable = false)
	private Suppliers supplier;

	public Pets getPet() {
		return pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public Suppliers getSupplier() {
		return supplier;
	}

	public void setSupplier(Suppliers supplier) {
		this.supplier = supplier;
	}

	public PetSupplierRelationshipId getId() {
		return id;
	}

	public void setId(PetSupplierRelationshipId id) {
		this.id = id;
	}
	
	

//	public PetSupplierRelationship(Pets pet, Suppliers supplier) {
//		super();
//		this.pet = pet;
//		this.supplier = supplier;
//	}
//
//	public PetSupplierRelationship() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public String toString() {
//		return "PetSupplierRelationship [pet=" + pet + ", supplier=" + supplier + "]";
//	}
//	
	
}
