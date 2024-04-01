package com.fdmgroup.javaproject.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	public boolean createTransaction(Transaction transaction) {
		Optional<Transaction> returnedTransaction = transactionRepo.findById(transaction.getTransactionID());
		
		if (returnedTransaction.isEmpty()) {
			transactionRepo.save(transaction);
			return true;
		} else {
			System.out.println("Transaction already exists.");
			return false;
		}
	}
	
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactionList = transactionRepo.findAll();
		return transactionList;
	}
	
	public List<Transaction> getAllByUser(User user) {
		List<Transaction> transactionList = transactionRepo.findByUser(user);
		return transactionList;
	} 
	
	public Map<String, Double> getTotalTransactionsByMonthAndType(User user, YearMonth yearMonth) {
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        
        List<Transaction> expenses = transactionRepo.findByUserAndDateBetweenAndType(user, startOfMonth, endOfMonth, "Expense");
        List<Transaction> incomes = transactionRepo.findByUserAndDateBetweenAndType(user, startOfMonth, endOfMonth, "Income");
        
        double totalExpenses = expenses.stream().mapToDouble(Transaction::getAmount).sum();
        double totalIncomes = incomes.stream().mapToDouble(Transaction::getAmount).sum();

        Map<String, Double> totals = new HashMap<>();
        totals.put("Expense", totalExpenses);
        totals.put("Income", totalIncomes);

        return totals;
    }
	
	public List<Transaction> findTransactionsForMonthAndYear(User user, int month, int year) {
        return transactionRepo.findByUserAndMonthAndYear(user, month, year);
    }
}
