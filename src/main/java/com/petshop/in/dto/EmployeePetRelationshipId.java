package com.petshop.in.dto;

//import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeePetRelationshipId{

	@Column(name = "pet_id")
	private int pet_id;
	
	@Column(name = "employee_id")
	private int employee_id;
	
}
