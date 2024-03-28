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
	private String userName;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	
	// constructors
	public User(int userID, String userName, String password, String email, String firstName, String lastName) {
		super();
		this.userID = userID;
		this.userName = userName;
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
		setUserName(updatedUser.getUserName());
		setPassword(updatedUser.getPassword());
		setEmail(updatedUser.getEmail());
		setFirstName(updatedUser.getFirstName());
		setLastName(updatedUser.getLastName());
	}
	
	// getters and setters
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
