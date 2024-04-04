package com.fdmgroup.javaproject.controller;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.AccountService;
import com.fdmgroup.javaproject.service.BudgetService;
import com.fdmgroup.javaproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BudgetService budgetService;

	@GetMapping("/dashboard/{id}")
	public String dashboard(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");

		if (user != null) {
			YearMonth currentMonth = YearMonth.now();
			int month = currentMonth.getMonthValue();
			int year = currentMonth.getYear();

			List<Budget> budgets = budgetService.getBudgetsByUserByMonthAndYear(month, year, user);
			List<Transaction> transactions = transactionService.getAllByUser(user);

			double totalBalance = accountService.getTotalAccountBalanceForUser(user);
			double initialBalance = accountService.getInitialAccountBalanceForUser(user);
			double amountDeducted = 0;
			double amountAdded = 0;
			for (Transaction transaction : transactions) {
				if (transaction.getType().equals("Expense")) {
					amountDeducted += transaction.getAmount();
				} else if (transaction.getType().equals("Income")) {
					amountAdded += transaction.getAmount();
				}
			}

			model.addAttribute("user", user);
			model.addAttribute("budgets", budgets);
			model.addAttribute("totalBalance", totalBalance);
			model.addAttribute("initialBalance", initialBalance);
			model.addAttribute("amountDeducted", amountDeducted);
			model.addAttribute("amountAdded", amountAdded);

			return "dashboard";
		} else {
			model.addAttribute("timeout", true);
			return "redirect:/login";
		}
	}

	@PostMapping("/dashboard/{id}")
	public String processMonthAndYearSelection(@PathVariable("id") String userid, @RequestParam("month") Integer month,
			@RequestParam("year") Integer year, HttpSession session, RedirectAttributes redirectAttributes) {
		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "redirect:/login";
		}

		redirectAttributes.addAttribute("month", month);
		redirectAttributes.addAttribute("year", year);

		int userId = user.getUserID();

		return "redirect:/dashboard/" + userId;
	}

}
