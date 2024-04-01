package com.fdmgroup.javaproject.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.AccountService;
import com.fdmgroup.javaproject.service.CategoryService;
import com.fdmgroup.javaproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	@Autowired
    private CategoryService categoryService;
	@Autowired
    private AccountService accountService;
	
	@GetMapping("/dashboard/transactions")
	public String transactions(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		List<Transaction> transactions = transactionService.getAllByUser(user);
		List<Category> categories = categoryService.getAllCategories();
		List<Account> accounts = accountService.getAllByUser(user);
		
		model.addAttribute("transactions", transactions);
		model.addAttribute("categories", categories);
		model.addAttribute("accounts", accounts);
		
		return("transactions");
	}
	
	@PostMapping("/dashboard/transactions")
	public String processTransaction( 	@RequestParam("category") int categoryID,
										@RequestParam("amount") double amount,
										@RequestParam("account") int accountID,
										@RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yy") LocalDate date,
										@RequestParam("type") String type,
										@RequestParam(value = "description", required = false) String description,
										HttpSession session) {
		
		Category category = categoryService.findById(categoryID);
		Account account = accountService.findById(accountID);
		User user = (User) session.getAttribute("user");
		
		Transaction newTransaction = new Transaction(date, amount, type, description, category, user, account);
		
		if (type.equals("Income")) {
			account.setBalance(account.getBalance() + amount);
		} else if (type.equals("Expense")) {
			account.setBalance(account.getBalance() - amount);
		}
		
		if (transactionService.createTransaction(newTransaction)) {
			return("redirect:/dashboard/transactions");
		} else {
		return("redirect:/dashboard/transactions");
		}
	}
	
	
}
