package com.petshop.in.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_categories")
public class PetCategories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PetCategories(int categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public PetCategories() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PetCategories [categoryId=" + categoryId + ", name=" + name + "]";
	}
	
	
	
}
