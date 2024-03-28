package com.fdmgroup.javaproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents the accounts a User has to track their budgets.
 * 
 * @author May Lwin
 */

@Entity
@Table(name = "Accounts")
public class Account {
	
	// attributes
	@Id
	@GeneratedValue
	private int accountID;
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	@Column(name = "BALANCE")
	private double balance;
	
	@ManyToOne
	@JoinColumn(name = "FK_USER_ID")
	private User user;
	
	// constructors
	public Account(int accountID, String accountName, double balance, User user) {
		super();
		this.accountID = accountID;
		this.accountName = accountName;
		this.balance = balance;
		this.user = user;
	}
	
	public Account () {}
	
	// behaviors
	
	/** 
	 * Updates user's accountName, balance, and User information.
	 * 
	 * @param updatedAccount 	account object with the same userId but with updated attributes.
	 */
	public void update(Account updatedAccount) {
		setAccountName(updatedAccount.getAccountName());
		setBalance(updatedAccount.getBalance());
		setUser(updatedAccount.getUser());
	}
	
	// getters and setters
	public int getAccountID() {
		return accountID;
	}
	
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
