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
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	
	// constructors
	public User(String username, String password, String email, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	

	public User() {}
	
	// behaviors
	
	/**
	 * Updates User's username, password, email, first name and last name.
	 * 
	 * @param updatedUser 	user object with the same userId but with updated attributes.
	 */
	public void update(User updatedUser) {
		setUsername(updatedUser.getUsername());
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
