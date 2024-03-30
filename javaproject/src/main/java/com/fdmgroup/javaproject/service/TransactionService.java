package com.fdmgroup.javaproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Transaction;
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
}
