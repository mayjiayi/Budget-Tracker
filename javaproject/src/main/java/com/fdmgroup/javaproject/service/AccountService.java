package com.fdmgroup.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.AccountRepository;




@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	public boolean createNewAccount(Account account) {
		Optional<Account> returnedAccount = accountRepo.findById(account.getAccountID());
		
		if (returnedAccount.isEmpty()) {
			accountRepo.save(account);
			return true;
		} else {
			System.out.println("Account already exists");
			return false;
		}
	}
	
	public List<Account> getAllAccounts() {
		List<Account> accountList = accountRepo.findAll();
		return accountList;
	}
	
	public Account findById(int id) {
		Optional<Account> account = accountRepo.findById(id);
		return account.get();
	}
	
	public List<Account> getAllByUser(User user) {
		List<Account> accountList = accountRepo.findByUser(user);
		return accountList;
	}
	
	public double getTotalAccountBalanceForUser(User user) {
		List<Account> accountList = accountRepo.findByUser(user);
		double totalBalance = 0.0;
		for (Account account : accountList) {
            totalBalance += account.getBalance();
        }
		return totalBalance;
	}
	
	public void updateAccount(Account account) {
		accountRepo.save(account);
	}
}
