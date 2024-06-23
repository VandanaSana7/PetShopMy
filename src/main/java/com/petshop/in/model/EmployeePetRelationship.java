package com.petshop.in.model;

import com.petshop.in.dto.EmployeePetRelationshipId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_pet_relationship")
public class EmployeePetRelationship {
	
	@EmbeddedId
	private EmployeePetRelationshipId id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
	private Employees employee;
	
	@ManyToOne
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id", insertable = false, updatable = false)
	private Pets pet;

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public Pets getPet() {
		return pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public EmployeePetRelationshipId getId() {
		return id;
	}

	public void setId(EmployeePetRelationshipId id) {
		this.id = id;
	}

//	public EmployeePetRelationship(Employees employee, Pets pet) {
//		super();
//		this.employee = employee;
//		this.pet = pet;
//	}
//
//	public EmployeePetRelationship() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public String toString() {
//		return "EmployeePetRelationship [employee=" + employee + ", pet=" + pet + "]";
//	}
	
	
}
