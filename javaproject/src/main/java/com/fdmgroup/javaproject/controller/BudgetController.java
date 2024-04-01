package com.fdmgroup.javaproject.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.AccountService;
import com.fdmgroup.javaproject.service.BudgetService;
import com.fdmgroup.javaproject.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BudgetController {
	
	@Autowired
	private BudgetService budgetService;
	@Autowired
    private CategoryService categoryService;
	@Autowired
    private AccountService accountService;
	
	@GetMapping("/dashboard/budgets")
	public String budgets(Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		List<Budget> budgets = budgetService.getAllByUser(user);
		List<Category> categories = categoryService.getAllCategories();
		List<Account> accounts = accountService.getAllByUser(user);
		
		model.addAttribute("budgets", budgets);
		model.addAttribute("categories", categories);
		model.addAttribute("accounts", accounts);
		return("budgets");
	}
	
	@PostMapping("/dashboard/budgets")
	public String processBudget(@RequestParam("startDate") LocalDate startDate,
            					@RequestParam("endDate") LocalDate endDate,
            					@RequestParam("targetAmount") double targetAmount,
            					@RequestParam("category") int categoryId,
            					HttpSession session) {
		
		Category category = categoryService.findById(categoryId);
		User user = (User) session.getAttribute("user");
		
		Budget newBudget = new Budget(startDate, endDate, targetAmount, user, category);
		
		if (budgetService.createBudget(newBudget)) {
			return("redirect:/dashboard/budgets");
		} else {
			return("redirect:/dashboard/budgets");
		}
	}
	

}
