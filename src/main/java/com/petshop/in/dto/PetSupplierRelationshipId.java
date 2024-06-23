package com.petshop.in.dto;

//import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PetSupplierRelationshipId //implements Serializable 
{
	
	@Column(name = "pet_id")
	private int pet_id;
	
	@Column(name = "supplier_id")
	private int supplier_id;
	
}
