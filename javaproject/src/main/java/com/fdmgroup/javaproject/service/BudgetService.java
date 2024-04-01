package com.fdmgroup.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.BudgetRepository;

/**
 * BudgetService is responsible for all methods related to manipulating the budget entities in the database.
 */
@Service
public class BudgetService {
	
	@Autowired
	private BudgetRepository budgetRepo;
	private static final Logger logger = LoggerFactory.getLogger(Budget.class);
	
	/**
	 * Creates Budget entities to be saved into the database
	 * 
	 * @param budget
	 * @return true if budget has been created, false if budget already exists in database
	 */
	public boolean createBudget(Budget budget) {
		Optional<Budget> returnedBudget = budgetRepo.findById(budget.getBudgetID());
		
		if (returnedBudget.isEmpty()) {
			budgetRepo.save(budget);
			return true;
		} else {
			logger.info("Budget already exists in database.");
			return false;
		}
		
	}
	
	/**
	 * Retrieves all budgets saved in the database
	 * 
	 * @return a list of budgets that have been saved in the database
	 */
	public List<Budget> getAllBudgets() {
		List<Budget> budgetList = budgetRepo.findAll();
		return budgetList;
	}
	
	/**
	 * Retrieves all budgets created by user saved in the database
	 * 
	 * @return a list of budgets that have been created by user and saved in the database
	 */
	public List<Budget> getAllByUser(User user) {
		List<Budget> budgetList = budgetRepo.findByUser(user);
		return budgetList;
	}

}
