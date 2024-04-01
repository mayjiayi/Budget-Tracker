package com.fdmgroup.javaproject.service;

import java.util.List;
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
}
