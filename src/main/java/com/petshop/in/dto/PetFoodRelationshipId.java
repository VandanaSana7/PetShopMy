package com.petshop.in.dto;

//import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PetFoodRelationshipId //implements Serializable
{

	@Column(name = "pet_id")
	private int pet_id;
	
	@Column(name = "food_id")
	private int food_id;
	
}
