package com.petshop.in.dto;

//import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PetGroomingRelationshipId //implements Serializable
{
	
	@Column(name = "pet_id")
	private int pet_id;
	
	@Column(name = "service_id")
	private int service_id;
	
}
