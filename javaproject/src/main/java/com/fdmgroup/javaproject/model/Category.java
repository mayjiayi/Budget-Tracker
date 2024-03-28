package com.fdmgroup.javaproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents the categories of transactions and budgets.
 */

@Entity
@Table(name = "Categories")
public class Category {
	
	// attributes
	@Id
	@GeneratedValue
	private int categoryID;
	@Column(name = "NAME")
	private String categoryName;
	
	@OneToOne
	@JoinColumn(name = "FK_BUDGET_ID")
	private Budget budget;
	
	
	// constructors
	public Category(int categoryID, String categoryName, Budget budget) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.budget = budget;
	}
	
	public Category() {}
	
	// behaviors
	
	/**
	 * Updates Category's name.
	 * 
	 * @param updatedCategory	category object with the same categoryId but with updated attributes.
	 */
	public void update(Category updatedCategory) {
		setName(updatedCategory.getName());
	}
	
	// getters and setters
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getName() {
		return categoryName;
	}
	public void setName(String name) {
		this.categoryName = name;
	}
	
	public Budget getBudget() {
		return budget;
	}
	public void setBudget(Budget budget) {
		this.budget = budget;
	}
}
