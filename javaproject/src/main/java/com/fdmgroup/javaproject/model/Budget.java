package com.fdmgroup.javaproject.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * Represents the budget set by the user for each category of spending.
 * 
 * @author May Lwin
 */

@Entity
@Table(name = "Budgets")
public class Budget {
	
	// attributes
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "budget_gen")
	@TableGenerator(name = "budget_gen", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_value", allocationSize = 1)
	private int budgetID;
	
	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	private LocalDate startDate;
	
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private LocalDate endDate;
	
	@Column(name = "TARGET_AMOUNT")
	private double targetAmount;
	
	@Column(name = "ACTUAL_SPENDING")
	private double actualSpending;
	
	@ManyToOne
	@JoinColumn(name = "FK_USER_ID")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "FK_CATEGORY_ID")
	private Category category;
	
	// constructors
	public Budget(LocalDate startDate, LocalDate endDate, double targetAmount,
			User user, Category category) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetAmount = targetAmount;
		this.actualSpending = 0;
		this.user = user;
		this.category = category;
	}
	
	public Budget() {}
	
	// behaviors
	
	/**
	 * Updates budget's name, startDate, endDate, targetAmount, actualSpending, User, and Category
	 * 
	 * @param updatedBudget		budget object with the same budgetId but with updated attributes.
	 */
	public void update(Budget updatedBudget) {
		setStartDate(updatedBudget.getStartDate());
		setEndDate(updatedBudget.getEndDate());
		setTargetAmount(updatedBudget.getTargetAmount());
		setActualSpending(updatedBudget.getActualSpending());
		setUser(updatedBudget.getUser());
		setCategory(updatedBudget.getCategory());
	}
	
	// getters and setters
	public int getBudgetID() {
		return budgetID;
	}
	public void setBudgetID(int budgetID) {
		this.budgetID = budgetID;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public double getTargetAmount() {
		return targetAmount;
	}
	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}
	public double getActualSpending() {
		return actualSpending;
	}
	public void setActualSpending(double actualSpending) {
		this.actualSpending = actualSpending;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
