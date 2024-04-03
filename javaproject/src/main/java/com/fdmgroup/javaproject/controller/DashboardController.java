package com.fdmgroup.javaproject.controller;

import java.time.YearMonth;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.AccountService;
import com.fdmgroup.javaproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private AccountService accountService;

	@GetMapping("/dashboard/{id}")
	public String dashboard(@PathVariable("id") String userid, @RequestParam(required = false) Integer month,
			@RequestParam(required = false) Integer year, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");

		if (user != null) {
			YearMonth selectedMonth = YearMonth.now();
			if (month != null && year != null) {
				selectedMonth = YearMonth.of(year, month);
			}

			double totalBalance = accountService.getTotalAccountBalanceForUser(user);

			Map<String, Double> totals = transactionService.getTotalTransactionsByMonthAndType(user, selectedMonth);

			model.addAttribute("totals", totals);
			model.addAttribute("selectedMonth", selectedMonth);
			model.addAttribute("user", user);
			model.addAttribute("totalBalance", totalBalance);

			return "dashboard";
		} else {
			// User is not logged in, redirect to login page
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
