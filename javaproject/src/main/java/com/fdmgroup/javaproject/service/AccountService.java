package com.fdmgroup.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.javaproject.model.Account;
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

}
