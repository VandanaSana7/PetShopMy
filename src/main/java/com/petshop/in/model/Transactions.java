package com.petshop.in.model;

import java.math.BigDecimal;
import java.util.Date;

import com.petshop.in.enums.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private int transactionId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customers customer;
	
	@ManyToOne
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
	private Pets pet;
	
	@Column(name = "transaction_date", columnDefinition = "date")
	private Date transactionDate;
	
	@Column(name = "amount", columnDefinition = "Decimal(10, 2)")
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status")
	private TransactionStatus transactionStatus;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public Pets getPet() {
		return pet;
	}

	public void setPet(Pets pet) {
		this.pet = pet;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Transactions(int transactionId, Customers customer, Pets pet, Date transactionDate, BigDecimal amount,
			TransactionStatus transactionStatus) {
		super();
		this.transactionId = transactionId;
		this.customer = customer;
		this.pet = pet;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.transactionStatus = transactionStatus;
	}

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId + ", customer=" + customer + ", pet=" + pet
				+ ", transactionDate=" + transactionDate + ", amount=" + amount + ", transactionStatus="
				+ transactionStatus + "]";
	}
	
	
	
}
