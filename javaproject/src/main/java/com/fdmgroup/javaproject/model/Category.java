package com.fdmgroup.javaproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.UniqueConstraint;

/**
 * Represents the categories of transactions and budgets.
 */

@Entity
@Table(	name = "Categories", 
uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category {
	
	// attributes
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "category_gen")
	@TableGenerator(name = "category_gen", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_value", allocationSize = 1)
	@Column(name = "categoryID") 
	private int categoryID; 
	@Column(name = "NAME")
	private String categoryName; 
	
	
	
	// constructors
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	public Category() {}
	
	// behaviors
	
	/**
	 * Updates Category's name.
	 * 
	 * @param updatedCategory	category object with the same categoryId but with updated attributes.
	 */
	public void update(Category updatedCategory) {
		setCategoryName(updatedCategory.getCategoryName());
	}
	 
	// getters and setters
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String name) {
		this.categoryName = name;
	}
}
