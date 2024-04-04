package com.fdmgroup.javaproject.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.AccountRepository;

/**
 * AccountService is responsible for all methods related to manipulating all
 * account entities
 * 
 * @author May Lwin
 */
@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;
	private static final Logger logger = LoggerFactory.getLogger(Account.class);

	/**
	 * Creates account entities to be saved into the database
	 * 
	 * @param account
	 * @return true if account has been created, false if account already exists in
	 *         database
	 */
	public boolean createNewAccount(Account account) {
		Optional<Account> returnedAccount = accountRepo.findById(account.getAccountID());

		if (returnedAccount.isEmpty()) {
			accountRepo.save(account);
			logger.info("Account has been saved into the database.");
			return true;
		} else {
			logger.info("Account already exists");
			return false;
		}
	}

	/**
	 * Retrieves all Accounts saved in the database
	 * 
	 * @return a list of Accounts that have been saved in the database
	 */
	public List<Account> getAllAccounts() {
		List<Account> accountList = accountRepo.findAll();
		return accountList;
	}

	/**
	 * Retrieves Account saved in the database by using accountIds
	 * 
	 * @param id
	 * @return account of the argument id in the database
	 */
	public Account findById(int id) {
		Optional<Account> account = accountRepo.findById(id);
		return account.get();
	}

	/**
	 * Retrieves Account saved in the database by using accountIds by the user
	 * 
	 * @param user
	 * @return accounts by the user in the database
	 */
	public List<Account> getAllByUser(User user) {
		List<Account> accountList = accountRepo.findByUser(user);
		return accountList;
	}

	/**
	 * Retrieves total account balance for user's accounts
	 * 
	 * @param user
	 * @return total account balance
	 */
	public double getTotalAccountBalanceForUser(User user) {
		List<Account> accountList = accountRepo.findByUser(user);
		double totalBalance = 0.0;
		for (Account account : accountList) {
			totalBalance += account.getBalance();
		}
		return totalBalance;
	}

	/**
	 * Updates account into database
	 * 
	 * @param account
	 */
	public void updateAccount(Account account) {
		accountRepo.save(account);
	}

	/**
	 * Returns the total initial account balance for a user
	 * 
	 * @param user
	 * @return double
	 */
	public double getInitialAccountBalanceForUser(User user) {
		List<Account> accountList = accountRepo.findByUser(user);
		double initialBalance = 0.0;
		for (Account account : accountList) {
			initialBalance += account.getInitialBalance();
		}
		return initialBalance;
	}

	/**
	 * Removes the account from the database
	 * 
	 * @param id
	 */
	public void deleteAccountById(int id) {
		accountRepo.deleteById(id);
	}
}
