package com.fdmgroup.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.repository.BudgetRepository;

@Service
public class BudgetService {
	
	@Autowired
	private BudgetRepository budgetRepo;
	
	public boolean createBudget(Budget budget) {
		Optional<Budget> returnedBudget = budgetRepo.findById(budget.getBudgetID());
		
		if (returnedBudget.isEmpty()) {
			budgetRepo.save(budget);
			return true;
		} else {
			System.out.println("Budget already exists");
			return false;
		}
		
	}
	
	public List<Budget> getAllBudgets() {
		List<Budget> budgetList = budgetRepo.findAll();
		return budgetList;
	}

}
