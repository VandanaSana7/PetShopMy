package com.petshop.in.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Suppliers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private int supplierId;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@Column(name = "contact_person", length = 50)
	private String contactPerson;
	
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private Addresses address;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}

	public Suppliers(int supplierId, String name, String contactPerson, String phoneNumber, String email,
			Addresses address) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.contactPerson = contactPerson;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}

	public Suppliers() {
		super();
	}

	@Override
	public String toString() {
		return "Suppliers [supplierId=" + supplierId + ", name=" + name + ", contactPerson=" + contactPerson
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + "]";
	}
	
	
}
