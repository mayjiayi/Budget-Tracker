package com.fdmgroup.javaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.AccountRepository;
import com.fdmgroup.javaproject.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

	@Mock
	private AccountRepository accountRepo;
	@Mock
	private User mockUser;

	@InjectMocks
	private AccountService accountService;

	@Test
	public void testCreateNewAccount_SuccessfulCreation() {
		// Arrange
		Account account = new Account("Test Account", 500, mockUser);
		when(accountRepo.findById(account.getAccountID())).thenReturn(Optional.empty());

		// Act
		boolean result = accountService.createNewAccount(account);

		// Assert
		assertTrue(result);
		verify(accountRepo, times(1)).findById(account.getAccountID());
		verify(accountRepo, times(1)).save(account);
	}

	@Test
	public void testCreateNewAccount_AccountAlreadyExists() {
		// Arrange
		Account existingAccount = new Account("Existing Account", 500, mockUser);
		when(accountRepo.findById(existingAccount.getAccountID())).thenReturn(Optional.of(existingAccount));

		// Act
		boolean result = accountService.createNewAccount(existingAccount);

		// Assert
		assertFalse(result);
		verify(accountRepo, times(1)).findById(existingAccount.getAccountID());
		verify(accountRepo, never()).save(any(Account.class));
	}

	@Test
	public void testGetAllAccounts_AccountsFound() {
		// Arrange
		List<Account> expectedAccounts = Arrays.asList(new Account("Test Account 1", 500, mockUser),
				new Account("Test Account 2", 500, mockUser), new Account("Test Account 3", 500, mockUser));
		when(accountRepo.findAll()).thenReturn(expectedAccounts);

		// Act
		List<Account> result = accountService.getAllAccounts();

		// Assert
		assertEquals(expectedAccounts.size(), result.size());
		assertEquals(expectedAccounts, result);
		verify(accountRepo, times(1)).findAll();
	}

	@Test
	public void testGetAllAccounts_NoAccountsFound() {
		// Arrange
		when(accountRepo.findAll()).thenReturn(Collections.emptyList());

		// Act
		List<Account> result = accountService.getAllAccounts();

		// Assert
		assertTrue(result.isEmpty());
		verify(accountRepo, times(1)).findAll();
	}

	@Test
	public void testFindById_AccountFound() {
		// Arrange
		int accountId = 1;
		Account expectedAccount = new Account("Test Account", 500, mockUser);
		when(accountRepo.findById(accountId)).thenReturn(Optional.of(expectedAccount));

		// Act
		Account result = accountService.findById(accountId);

		// Assert
		assertNotNull(result);
		assertEquals(expectedAccount, result);
		verify(accountRepo, times(1)).findById(accountId);
	}

	@Test
	public void testGetAllByUser_UserHasAccounts() {
		// Arrange
		User user = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		Account account1 = new Account("Test Account 1", 500, user);
		Account account2 = new Account("Test Account 2", 500, user);
		List<Account> expectedAccounts = Arrays.asList(account1, account2);
		when(accountRepo.findByUser(user)).thenReturn(expectedAccounts);

		// Act
		List<Account> result = accountService.getAllByUser(user);

		// Assert
		assertEquals(expectedAccounts.size(), result.size());
		assertEquals(expectedAccounts, result);
		verify(accountRepo, times(1)).findByUser(user);
	}

	@Test
	public void testGetAllByUser_UserHasNoAccounts() {
		// Arrange
		User user = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		when(accountRepo.findByUser(user)).thenReturn(Collections.emptyList());

		// Act
		List<Account> result = accountService.getAllByUser(user);

		// Assert
		assertTrue(result.isEmpty());
		verify(accountRepo, times(1)).findByUser(user);
	}

	@Test
	public void testGetTotalAccountBalanceForUser_UserHasAccounts() {
		// Arrange
		User user = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		Account account1 = new Account("Test Account 1", 500, user);
		Account account2 = new Account("Test Account 2", 500, user);
		List<Account> accounts = Arrays.asList(account1, account2);
		when(accountRepo.findByUser(user)).thenReturn(accounts);

		// Act
		double totalBalance = accountService.getTotalAccountBalanceForUser(user);

		// Assert
		assertEquals(1000.0, totalBalance);
		verify(accountRepo, times(1)).findByUser(user);
	}

	@Test
	public void testGetTotalAccountBalanceForUser_UserHasNoAccounts() {
		// Arrange
		User user = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		when(accountRepo.findByUser(user)).thenReturn(Collections.emptyList());

		// Act
		double totalBalance = accountService.getTotalAccountBalanceForUser(user);

		// Assert
		assertEquals(0.0, totalBalance);
		verify(accountRepo, times(1)).findByUser(user);
	}

	@Test
	public void testGetInitialAccountBalanceForUser_UserHasAccounts() {
		// Arrange
		User user = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		Account account1 = new Account("Test Account 1", 500, user); // initial balance = 50
		Account account2 = new Account("Test Account 2", 500, user); // initial balance = 100
		List<Account> accounts = Arrays.asList(account1, account2);
		when(accountRepo.findByUser(user)).thenReturn(accounts);

		// Act
		double initialBalance = accountService.getInitialAccountBalanceForUser(user);

		// Assert
		assertEquals(1000.0, initialBalance); // total initial balance = 50 + 100
		verify(accountRepo, times(1)).findByUser(user);
	}

	@Test
	public void testGetInitialAccountBalanceForUser_UserHasNoAccounts() {
		// Arrange
		User user = new User("testUsername", "testPassword", "testEmail", "testFirstName", "testLastName");
		when(accountRepo.findByUser(user)).thenReturn(Collections.emptyList());

		// Act
		double initialBalance = accountService.getInitialAccountBalanceForUser(user);

		// Assert
		assertEquals(0.0, initialBalance); // total initial balance should be 0
		verify(accountRepo, times(1)).findByUser(user);
	}

	@Test
	public void testDeleteAccountById() {
		// Arrange
		int accountId = 1;

		// Act
		accountService.deleteAccountById(accountId);

		// Assert
		verify(accountRepo, times(1)).deleteById(accountId);
	}

}
