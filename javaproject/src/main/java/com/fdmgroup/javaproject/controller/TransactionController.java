package com.fdmgroup.javaproject.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.AccountService;
import com.fdmgroup.javaproject.service.BudgetService;
import com.fdmgroup.javaproject.service.CategoryService;
import com.fdmgroup.javaproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

/**
 * TransactionController is responsible for html related to transaction entities
 */
@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BudgetService budgetService;

	private static final Logger logger = LogManager.getLogger(TransactionController.class);

	@GetMapping("/dashboard/transactions")
	public String transactions(@RequestParam(name = "accountId", required = false) Integer accountId, Model model,
			HttpSession session) {
		User user = (User) session.getAttribute("user");

		if (user != null) {
			List<Transaction> transactions;

			if (accountId != null) {
				Account targetAccount = accountService.findById(accountId);
				transactions = transactionService.findTransactionsForAccount(targetAccount);
			} else {
				transactions = transactionService.getAllByUser(user);
			}

			List<Category> categories = categoryService.getAllCategories();
			List<Account> accounts = accountService.getAllByUser(user);

			model.addAttribute("transactions", transactions);
			model.addAttribute("categories", categories);
			model.addAttribute("accounts", accounts);

			logger.info("added user's transactions to model attribute");
			logger.info("added user's categories to model attribute");
			logger.info("added user's accounts to model attribute");

			return ("transactions");
		} else {
			model.addAttribute("timeout", true);
			return "redirect:/login";
		}

	}

	@PostMapping("/dashboard/transactions")
	public String processTransaction(@RequestParam("category") int categoryID, @RequestParam("amount") double amount,
			@RequestParam("account") int accountID,
			@RequestParam("date") @DateTimeFormat(pattern = "dd/MM/yy") LocalDate date,
			@RequestParam("type") String type,
			@RequestParam(value = "description", required = false) String description, HttpSession session) {

		Category category = categoryService.findById(categoryID);
		Account account = accountService.findById(accountID);
		User user = (User) session.getAttribute("user");

		Transaction newTransaction = new Transaction(date, amount, type, description, category, user, account);

		if (type.equals("Income")) {
			account.setBalance(account.getBalance() + amount);
			logger.info("Account '" + account.getAccountName() + "' balance updated in database.");
		} else if (type.equals("Expense")) {
			account.setBalance(account.getBalance() - amount);
			logger.info("Account '" + account.getAccountName() + "' balance updated in database.");
		}

		List<Budget> budget = budgetService.findBudgetByCategoryAndDate(category, date);
		if (!budget.isEmpty()) {
			if (date.compareTo(budget.get(0).getStartDate()) >= 0 && date.compareTo(budget.get(0).getEndDate()) <= 0) {

				budget.get(0).setActualSpending(budget.get(0).getActualSpending() + amount);
				logger.info("Budget for category '" + category.getCategoryName() + "' updated in database.");
			}
		}

		if (transactionService.createTransaction(newTransaction)) {
			logger.info("Transaction has been created and saved in database");
			return ("redirect:/dashboard/transactions");
		} else {
			logger.info("Unable to create transaction.");
			return ("redirect:/dashboard/transactions");
		}
	}

	@PostMapping("/dashboard/transactions/delete")
	public String deleteTransaction(int transactionId) {
		Optional<Transaction> transaction = transactionService.findTransactionById(transactionId);
		if (transaction.isPresent()) {

			Transaction targetTransaction = transaction.get();
			Account targetAccount = targetTransaction.getAccount();
			List<Budget> targetBudget = budgetService.findBudgetByCategoryAndDate(targetTransaction.getCategory(),
					targetTransaction.getDate());

			if (targetTransaction.getType().equals("Income")) {
				targetAccount.setBalance(targetAccount.getBalance() - targetTransaction.getAmount());
				logger.info("Account '" + targetAccount.getAccountName() + "' balance updated in database.");
			} else if (targetTransaction.getType().equals("Expense")) {
				targetAccount.setBalance(targetAccount.getBalance() + targetTransaction.getAmount());
				logger.info("Account '" + targetAccount.getAccountName() + "' balance updated in database.");
			}

			if (!targetBudget.isEmpty()) {
				if (targetTransaction.getDate().compareTo(targetBudget.get(0).getStartDate()) >= 0
						&& targetTransaction.getDate().compareTo(targetBudget.get(0).getEndDate()) <= 0) {

					targetBudget.get(0)
							.setActualSpending(targetBudget.get(0).getActualSpending() - targetTransaction.getAmount());
					logger.info("Budget for category '" + targetTransaction.getCategory().getCategoryName()
							+ "' updated in database.");
				}
			}

		}
		transactionService.deleteTransactionById(transactionId);
		return ("redirect:/dashboard/transactions");
	}
}
