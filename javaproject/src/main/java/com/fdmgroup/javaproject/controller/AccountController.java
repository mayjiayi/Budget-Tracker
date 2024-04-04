package com.fdmgroup.javaproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.AccountService;
import com.fdmgroup.javaproject.service.BudgetService;
import com.fdmgroup.javaproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

/**
 * AccountController is responsible for returning html templates for anything
 * account entity related
 */
@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private BudgetService budgetService;

	private static final Logger logger = LoggerFactory.getLogger(Account.class);

	@GetMapping("/dashboard/accounts")
	public String accounts(Model model, HttpSession session) {

		User user = (User) session.getAttribute("user");

		if (user != null) {
			List<Account> accounts = accountService.getAllByUser(user);
			model.addAttribute("accounts", accounts);
			logger.info("added user's accounts in model attribute");
			return ("accounts");
		} else {
			model.addAttribute("timeout", true);
			return "redirect:/login";
		}
	}

	@PostMapping("/dashboard/accounts")
	public String createAccount(@RequestParam String accountName, @RequestParam double balance, HttpSession session) {
		logger.info("Creating account...");

		User user = (User) session.getAttribute("user");

		Account newAccount = new Account(accountName, balance, user);
		logger.info("Account Name : " + accountName + " | Balance : " + balance + " | User : " + user.getUserID());

		if (accountService.createNewAccount(newAccount)) {
			logger.info("Account '" + accountName + "' created and saved in the database");
			return ("redirect:/dashboard/accounts");
		} else {
			logger.info("Unable to create '" + accountName);
			return ("redirect:/dashboard/accounts");
		}
	}

	@PostMapping("/dashboard/accounts/delete")
	public String deleteAccount(int accountId) {
		Account account = accountService.findById(accountId);
		List<Transaction> transactionList = transactionService.findTransactionsForAccount(account);

		for (Transaction transaction : transactionList) {
			List<Budget> targetBudget = budgetService.findBudgetByCategoryAndDate(transaction.getCategory(),
					transaction.getDate());
			if (!targetBudget.isEmpty()) {
				if (transaction.getDate().compareTo(targetBudget.get(0).getStartDate()) >= 0
						&& transaction.getDate().compareTo(targetBudget.get(0).getEndDate()) <= 0) {
					targetBudget.get(0)
							.setActualSpending(targetBudget.get(0).getActualSpending() - transaction.getAmount());
					logger.info("Budget for category '" + transaction.getCategory().getCategoryName()
							+ "' updated in database.");
				}
			}
			transactionService.deleteTransactionById(transaction.getTransactionID());
		}
		accountService.deleteAccountById(accountId);
		return ("redirect:/dashboard/accounts");
	}
}
