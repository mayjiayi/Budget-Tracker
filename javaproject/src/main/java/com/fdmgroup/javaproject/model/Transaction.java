package com.fdmgroup.javaproject.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Represents the transactions that a User records with its associating category and account.
 * 
 * @author May Lwin
 */
@Entity
@Table(name = "Transactions")
public class Transaction {
	
	// attributes
	@Id
	@GeneratedValue
	private int transactionID;
	@Column(name = "DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "AMOUNT")
	private double amount;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "FK_CATEGORY_ID")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "FK_USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "FK_ACCOUNT_ID")
	private Account account;
	
	
	// constructors
	public Transaction(int transactionID, double amount, String type, String description,
			Category category, User user, Account account) {
		super();
		this.transactionID = transactionID;
		this.date = new Date();
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.category = category;
		this.user = user;
		this.account = account;
	}
	
	public Transaction() {}
	
	// behaviors
	
	/**
	 * Updates Transactions's date, amount, description, type, category, user, and account.
	 * 
	 * @param updatedTransaction	transaction object with the same transactionID but with updated attributes.
	 */
	public void update(Transaction updatedTransaction) {
		setDate(updatedTransaction.getDate());
		setAmount(updatedTransaction.getAmount());
		setDescription(updatedTransaction.getDescription());
		setType(updatedTransaction.getType());
		setCategory(updatedTransaction.getCategory());
		setUser(updatedTransaction.getUser());
		setAccount(updatedTransaction.getAccount());
	}
	
	// getters and setters
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
