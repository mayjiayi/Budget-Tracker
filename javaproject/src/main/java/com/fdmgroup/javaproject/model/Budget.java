package com.fdmgroup.javaproject.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
	@GeneratedValue
	private int budgetID;
	
	@Column(name = "BUDGET_NAME")
	private String budgetName;
	
	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
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
	public Budget(int budgetID, String budgetName,Date startDate, Date endDate, double targetAmount, double actualSpending,
			User user, Category category) {
		super();
		this.budgetID = budgetID;
		this.budgetName = budgetName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetAmount = targetAmount;
		this.actualSpending = actualSpending;
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
		setBudgetName(updatedBudget.getBudgetName());
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
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

	public String getBudgetName() {
		return budgetName;
	}
	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
