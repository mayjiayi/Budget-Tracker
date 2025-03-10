package com.fdmgroup.javaproject.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.BudgetRepository;

/**
 * BudgetService is responsible for all methods related to manipulating the
 * budget entities in the database.
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
	 * @return true if budget has been created, false if budget already exists in
	 *         database
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
	 * @return a list of budgets that have been created by user and saved in the
	 *         database
	 */
	public List<Budget> getAllByUser(User user) {
		List<Budget> budgetList = budgetRepo.findByUser(user);
		return budgetList;
	}

	/**
	 * Returns a list of budgets filtered by month and year
	 * 
	 * @param month
	 * @param year
	 * @return list of budgets
	 */
	public List<Budget> getBudgetsByMonthAndYear(Integer month, Integer year) {
		// Get the start and end dates of the provided month and year
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.plusMonths(1).minusDays(1);

		// Query the repository to retrieve budgets within the specified month and year
		return budgetRepo.findByStartDateBetween(startDate, endDate);
	}

	/**
	 * Returns a list of budget filtered by Category and Date
	 * 
	 * @param category
	 * @param date
	 * @return list of budgets
	 */
	public List<Budget> findBudgetByCategoryAndDate(Category category, LocalDate date) {
		return budgetRepo.findByCategoryCategoryIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
				category.getCategoryID(), date, date);
	}

	/**
	 * Returns a list of budget filtered by user, month and year
	 * 
	 * @param month
	 * @param year
	 * @param user
	 * @return list of budgets
	 */
	public List<Budget> getBudgetsByUserByMonthAndYear(Integer month, Integer year, User user) {
		List<Budget> budgetList = getBudgetsByMonthAndYear(month, year);
		List<Budget> userBudgetList = new ArrayList<>();

		for (Budget budget : budgetList) {
			if (budget.getUser().getUserID() == user.getUserID()) {
				userBudgetList.add(budget);
			}
		}
		return userBudgetList;
	}

	/**
	 * Removes budget from database
	 * 
	 * @param id
	 */
	public void deleteBudgetById(int id) {
		budgetRepo.deleteById(id);
	}
}
