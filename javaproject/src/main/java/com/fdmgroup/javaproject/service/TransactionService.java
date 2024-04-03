package com.fdmgroup.javaproject.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.TransactionRepository;

/**
 * TransactionService is responsible for all methods related to manipulating the
 * Transaction entity in the database.
 * 
 * @author May Lwin
 */
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepo;
	private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

	/**
	 * Creates Transaction entities to be saved into the database
	 * 
	 * @param transaction
	 * @return true if transaction has been created, false if transaction already
	 *         exists in database
	 */
	public boolean createTransaction(Transaction transaction) {
		Optional<Transaction> returnedTransaction = transactionRepo.findById(transaction.getTransactionID());

		if (returnedTransaction.isEmpty()) {
			transactionRepo.save(transaction);
			return true;
		} else {
			logger.info("Transaction already exists.");
			return false;
		}
	}

	/**
	 * Retrieves all transactions saved in the database
	 * 
	 * @return a list of transactions that have been saved in the database
	 */
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactionList = transactionRepo.findAll();
		return transactionList;
	}

	/**
	 * Retrieves all transactions in the database saved by the user
	 * 
	 * @return a list of transactions that have been saved in the database
	 */
	public List<Transaction> getAllByUser(User user) {
		List<Transaction> transactionList = transactionRepo.findByUser(user);
		return transactionList;
	}

	/**
	 * Retrieves total of transactions in the month and year filtered by user
	 * 
	 * @param user
	 * @param yearMonth
	 * @return map of total balance for incomes and expenses
	 */
	public Map<String, Double> getTotalTransactionsByMonthAndType(User user, YearMonth yearMonth) {
		LocalDate startOfMonth = yearMonth.atDay(1);
		LocalDate endOfMonth = yearMonth.atEndOfMonth();

		List<Transaction> expenses = transactionRepo.findByUserAndDateBetweenAndType(user, startOfMonth, endOfMonth,
				"Expense");
		List<Transaction> incomes = transactionRepo.findByUserAndDateBetweenAndType(user, startOfMonth, endOfMonth,
				"Income");

		double totalExpenses = expenses.stream().mapToDouble(Transaction::getAmount).sum();
		double totalIncomes = incomes.stream().mapToDouble(Transaction::getAmount).sum();

		Map<String, Double> totals = new HashMap<>();
		totals.put("Expense", totalExpenses);
		totals.put("Income", totalIncomes);

		return totals;
	}

	/**
	 * Retrieves list of transactions filtered by user, year and month
	 * 
	 * @param user
	 * @param month
	 * @param year
	 * @return list of transactions
	 */
	public List<Transaction> findTransactionsForMonthAndYear(User user, int month, int year) {
		return transactionRepo.findByUserAndMonthAndYear(user, month, year);
	}

	public List<Transaction> findTransactionsForAccount(Account account) {

		return transactionRepo.findByAccount(account);
	}
}
