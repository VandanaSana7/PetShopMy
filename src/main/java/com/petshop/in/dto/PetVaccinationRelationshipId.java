package com.petshop.in.dto;

//import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PetVaccinationRelationshipId //implements Serializable 
{

	@Column(name = "pet_id")
	private int pet_id;
	
	@Column(name = "vaccination_id")
	private int vaccination_id;
	
}
