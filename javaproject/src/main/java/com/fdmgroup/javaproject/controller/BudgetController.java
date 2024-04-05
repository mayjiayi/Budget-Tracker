package com.fdmgroup.javaproject.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(BudgetController.class);

	@GetMapping("/dashboard/budgets")
	public String budgets(@RequestParam(required = false) Integer month, @RequestParam(required = false) Integer year,
			Model model, HttpSession session) {

		User user = (User) session.getAttribute("user");

		if (user != null) {
			List<Budget> budgets;
			if (month != null && year != null) {
				budgets = budgetService.getBudgetsByUserByMonthAndYear(month, year, user);
			} else {
				budgets = budgetService.getAllByUser(user);
			}

			List<Category> categories = categoryService.getAllCategories();
			List<Account> accounts = accountService.getAllByUser(user);

			model.addAttribute("budgets", budgets);
			model.addAttribute("categories", categories);
			model.addAttribute("accounts", accounts);

			logger.info("added user's budgets in model attribute");
			logger.info("added user's categories in model attribute");
			logger.info("added user's accounts in model attribute");
			return ("budgets");
		} else {
			model.addAttribute("timeout", true);
			return "redirect:/login";
		}
	}

	@PostMapping("/dashboard/budgets")
	public String processBudget(@RequestParam("startDate") LocalDate startDate,
			@RequestParam("endDate") LocalDate endDate, @RequestParam("targetAmount") double targetAmount,
			@RequestParam("category") int categoryId, HttpSession session) {

		Category category = categoryService.findById(categoryId);
		User user = (User) session.getAttribute("user");

		Budget newBudget = new Budget(startDate, endDate, targetAmount, user, category);

		if (budgetService.createBudget(newBudget)) {
			logger.info(
					"Budget for category '" + category.getCategoryName() + "' has been created and saved in database");
			return ("redirect:/dashboard/budgets");
		} else {
			logger.warn("Unable to create budget for category '" + category.getCategoryName());
			return ("redirect:/dashboard/budgets");
		}
	}

	@PostMapping("/dashboard/budgets/delete")
	public String deleteBudget(int budgetId) {
		budgetService.deleteBudgetById(budgetId);
		return ("redirect:/dashboard/budgets");
	}
}
