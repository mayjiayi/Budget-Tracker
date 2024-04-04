package com.fdmgroup.javaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.TransactionRepository;
import com.fdmgroup.javaproject.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

	@Mock
	private TransactionRepository transactionRepo;
	@Mock
	private LocalDate mockDate;
	@Mock
	private Category mockCategory;
	@Mock
	private User mockUser;
	@Mock
	private Account mockAccount;

	@InjectMocks
	private TransactionService transactionService;

	@Test
	public void testCreateTransaction_NewTransaction() {
		// Arrange
		Transaction transaction = new Transaction(mockDate, 5, "Expense", "Lunch", mockCategory, mockUser, mockAccount);
		when(transactionRepo.findById(transaction.getTransactionID())).thenReturn(Optional.empty());

		// Act
		boolean result = transactionService.createTransaction(transaction);

		// Assert
		assertTrue(result);
		verify(transactionRepo, times(1)).findById(transaction.getTransactionID());
		verify(transactionRepo, times(1)).save(transaction);
	}

	@Test
	public void testCreateTransaction_TransactionAlreadyExists() {
		// Arrange
		Transaction existingTransaction = new Transaction(mockDate, 5, "Expense", "Lunch", mockCategory, mockUser,
				mockAccount);
		when(transactionRepo.findById(existingTransaction.getTransactionID()))
				.thenReturn(Optional.of(existingTransaction));

		// Act
		boolean result = transactionService.createTransaction(existingTransaction);

		// Assert
		assertFalse(result);
		verify(transactionRepo, times(1)).findById(existingTransaction.getTransactionID());
		verify(transactionRepo, never()).save(any(Transaction.class));
	}

	@Test
	public void testGetAllTransactions_TransactionsFound() {
		// Arrange
		List<Transaction> expectedTransactions = Arrays.asList(
				new Transaction(mockDate, 5, "Expense", "Breakfast", mockCategory, mockUser, mockAccount),
				new Transaction(mockDate, 6, "Expense", "Lunch", mockCategory, mockUser, mockAccount),
				new Transaction(mockDate, 7, "Expense", "Dinner", mockCategory, mockUser, mockAccount));
		when(transactionRepo.findAll()).thenReturn(expectedTransactions);

		// Act
		List<Transaction> result = transactionService.getAllTransactions();

		// Assert
		assertEquals(expectedTransactions.size(), result.size());
		assertEquals(expectedTransactions, result);
		verify(transactionRepo, times(1)).findAll();
	}

	@Test
	public void testGetAllTransactions_NoTransactionsFound() {
		// Arrange
		when(transactionRepo.findAll()).thenReturn(Collections.emptyList());

		// Act
		List<Transaction> result = transactionService.getAllTransactions();

		// Assert
		assertEquals(0, result.size());
		verify(transactionRepo, times(1)).findAll();
	}

	@Test
	public void testGetAllByUser_UserHasTransactions() {
		// Arrange
		List<Transaction> expectedTransactions = Arrays.asList(
				new Transaction(mockDate, 5, "Expense", "Breakfast", mockCategory, mockUser, mockAccount),
				new Transaction(mockDate, 6, "Expense", "Lunch", mockCategory, mockUser, mockAccount),
				new Transaction(mockDate, 7, "Expense", "Dinner", mockCategory, mockUser, mockAccount));
		when(transactionRepo.findByUser(mockUser)).thenReturn(expectedTransactions);

		// Act
		List<Transaction> result = transactionService.getAllByUser(mockUser);

		// Assert
		assertEquals(expectedTransactions.size(), result.size());
		assertEquals(expectedTransactions, result);
		verify(transactionRepo, times(1)).findByUser(mockUser);
	}

	@Test
	public void testGetAllByUser_UserHasNoTransactions() {
		// Arrange
		when(transactionRepo.findByUser(mockUser)).thenReturn(Collections.emptyList());

		// Act
		List<Transaction> result = transactionService.getAllByUser(mockUser);

		// Assert
		assertEquals(0, result.size());
		verify(transactionRepo, times(1)).findByUser(mockUser);
	}

	@Test
	public void testGetTotalTransactionsByMonthAndType() {
		// Arrange
		YearMonth yearMonth = YearMonth.of(2024, 4); // April 2024

		LocalDate startOfMonth = yearMonth.atDay(1);
		LocalDate endOfMonth = yearMonth.atEndOfMonth();

		List<Transaction> expenses = Arrays.asList(
				new Transaction(LocalDate.of(2024, 4, 5), 5, "Expense", "Breakfast", mockCategory, mockUser,
						mockAccount),
				new Transaction(LocalDate.of(2024, 4, 10), 5, "Expense", "Breakfast", mockCategory, mockUser,
						mockAccount));

		List<Transaction> incomes = Arrays.asList(
				new Transaction(LocalDate.of(2024, 4, 15), 50, "Income", "tuition", mockCategory, mockUser,
						mockAccount),
				new Transaction(LocalDate.of(2024, 4, 20), 45, "Income", "clothes", mockCategory, mockUser,
						mockAccount));

		when(transactionRepo.findByUserAndDateBetweenAndType(mockUser, startOfMonth, endOfMonth, "Expense"))
				.thenReturn(expenses);
		when(transactionRepo.findByUserAndDateBetweenAndType(mockUser, startOfMonth, endOfMonth, "Income"))
				.thenReturn(incomes);

		// Act
		Map<String, Double> result = transactionService.getTotalTransactionsByMonthAndType(mockUser, yearMonth);

		// Assert
		assertEquals(10.0, result.get("Expense"));
		assertEquals(95, result.get("Income"));
		verify(transactionRepo, times(1)).findByUserAndDateBetweenAndType(mockUser, startOfMonth, endOfMonth,
				"Expense");
		verify(transactionRepo, times(1)).findByUserAndDateBetweenAndType(mockUser, startOfMonth, endOfMonth, "Income");
	}

	@Test
	public void testFindTransactionsForMonthAndYear_TransactionsFound() {
		// Arrange
		int month = 4; // April
		int year = 2024;

		List<Transaction> expectedTransactions = Arrays.asList(
				new Transaction(LocalDate.of(2024, 4, 5), 5, "Expense", "Breakfast", mockCategory, mockUser,
						mockAccount),
				new Transaction(LocalDate.of(2024, 4, 10), 5, "Expense", "Breakfast", mockCategory, mockUser,
						mockAccount));
		when(transactionRepo.findByUserAndMonthAndYear(mockUser, month, year)).thenReturn(expectedTransactions);

		// Act
		List<Transaction> result = transactionService.findTransactionsForMonthAndYear(mockUser, month, year);

		// Assert
		assertEquals(expectedTransactions.size(), result.size());
		assertEquals(expectedTransactions, result);
		verify(transactionRepo, times(1)).findByUserAndMonthAndYear(mockUser, month, year);
	}

	@Test
	public void testFindTransactionsForMonthAndYear_NoTransactionsFound() {
		// Arrange
		int month = 5;
		int year = 2024;

		when(transactionRepo.findByUserAndMonthAndYear(mockUser, month, year)).thenReturn(Collections.emptyList());

		// Act
		List<Transaction> result = transactionService.findTransactionsForMonthAndYear(mockUser, month, year);

		// Assert
		assertEquals(0, result.size());
		verify(transactionRepo, times(1)).findByUserAndMonthAndYear(mockUser, month, year);
	}

	@Test
	public void testFindTransactionsForAccount_TransactionsFound() {
		// Arrange
		List<Transaction> expectedTransactions = Arrays.asList(
				new Transaction(mockDate, 5, "Expense", "Breakfast", mockCategory, mockUser, mockAccount),
				new Transaction(mockDate, 5, "Expense", "Breakfast", mockCategory, mockUser, mockAccount));
		when(transactionRepo.findByAccount(mockAccount)).thenReturn(expectedTransactions);

		// Act
		List<Transaction> result = transactionService.findTransactionsForAccount(mockAccount);

		// Assert
		assertEquals(expectedTransactions.size(), result.size());
		assertEquals(expectedTransactions, result);
		verify(transactionRepo, times(1)).findByAccount(mockAccount);
	}

	@Test
	public void testFindTransactionsForAccount_NoTransactionsFound() {
		// Arrange
		when(transactionRepo.findByAccount(mockAccount)).thenReturn(Collections.emptyList());

		// Act
		List<Transaction> result = transactionService.findTransactionsForAccount(mockAccount);

		// Assert
		assertEquals(0, result.size());
		verify(transactionRepo, times(1)).findByAccount(mockAccount);
	}

	@Test
	public void testDeleteTransactionById() {
		// Arrange
		int transactionId = 1;

		// Act
		transactionService.deleteTransactionById(transactionId);

		// Assert
		verify(transactionRepo, times(1)).deleteById(transactionId);
	}

	@Test
	public void testFindTransactionById_TransactionFound() {
		// Arrange
		int transactionId = 1;
		Transaction expectedTransaction = new Transaction(mockDate, 5, "Expense", "Breakfast", mockCategory, mockUser,
				mockAccount);
		when(transactionRepo.findById(transactionId)).thenReturn(Optional.of(expectedTransaction));

		// Act
		Optional<Transaction> result = transactionService.findTransactionById(transactionId);

		// Assert
		assertEquals(Optional.of(expectedTransaction), result);
		verify(transactionRepo, times(1)).findById(transactionId);
	}

	@Test
	public void testFindTransactionById_TransactionNotFound() {
		// Arrange
		int transactionId = 1;
		when(transactionRepo.findById(transactionId)).thenReturn(Optional.empty());

		// Act
		Optional<Transaction> result = transactionService.findTransactionById(transactionId);

		// Assert
		assertEquals(Optional.empty(), result);
		verify(transactionRepo, times(1)).findById(transactionId);
	}
}
