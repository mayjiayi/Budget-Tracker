package com.fdmgroup.javaproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Represents the Users in the budgetTracker.
 */
@Entity
@Table(name = "Users")
public class User {
	
	// attributes
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private int userID;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "EMAIL")
	private String email;
	
	// constructors
	public User(String userName, String password, String email) {
		super();
		this.username = userName;
		this.password = password;
		this.email = email;
	}
	
	public User() {}
	
	// behaviors
	
	/**
	 * Updates User's username, password, email, first name and last name.
	 * 
	 * @param updatedUser 	user object with the same userId but with updated attributes.
	 */
	public void update(User updatedUser) {
		setUserName(updatedUser.getUserName());
		setPassword(updatedUser.getPassword());
		setEmail(updatedUser.getEmail());
	}
	
	// getters and setters
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
