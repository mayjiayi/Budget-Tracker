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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.BudgetRepository;
import com.fdmgroup.javaproject.service.BudgetService;

@ExtendWith(MockitoExtension.class)
public class BudgetServiceTests {

	@Mock
	private BudgetRepository budgetRepo;
	@Mock
	private LocalDate mockDate1;
	@Mock
	private LocalDate mockDate2;
	@Mock
	private User mockUser;
	@Mock
	private Category mockCategory;

	@InjectMocks
	private BudgetService budgetService;

	@Test
	public void testCreateBudget_NewBudget() {
		// Arrange
		Budget budget = new Budget(mockDate1, mockDate2, 500, mockUser, mockCategory);
		when(budgetRepo.findById(budget.getBudgetID())).thenReturn(Optional.empty());

		// Act
		boolean result = budgetService.createBudget(budget);

		// Assert
		assertTrue(result);
		verify(budgetRepo, times(1)).findById(budget.getBudgetID());
		verify(budgetRepo, times(1)).save(budget);
	}

	@Test
	public void testCreateBudget_BudgetAlreadyExists() {
		// Arrange
		Budget existingBudget = new Budget(mockDate1, mockDate2, 500, mockUser, mockCategory);
		when(budgetRepo.findById(existingBudget.getBudgetID())).thenReturn(Optional.of(existingBudget));

		// Act
		boolean result = budgetService.createBudget(existingBudget);

		// Assert
		assertFalse(result);
		verify(budgetRepo, times(1)).findById(existingBudget.getBudgetID());
		verify(budgetRepo, never()).save(any(Budget.class));
	}

	@Test
	public void testGetAllBudgets_BudgetsFound() {
		// Arrange
		List<Budget> expectedBudgets = Arrays.asList(new Budget(mockDate1, mockDate2, 500, mockUser, mockCategory),
				new Budget(mockDate1, mockDate2, 500, mockUser, mockCategory),
				new Budget(mockDate1, mockDate2, 500, mockUser, mockCategory));
		when(budgetRepo.findAll()).thenReturn(expectedBudgets);

		// Act
		List<Budget> result = budgetService.getAllBudgets();

		// Assert
		assertEquals(expectedBudgets.size(), result.size());
		assertEquals(expectedBudgets, result);
		verify(budgetRepo, times(1)).findAll();
	}

	@Test
	public void testGetAllBudgets_NoBudgetsFound() {
		// Arrange
		when(budgetRepo.findAll()).thenReturn(Collections.emptyList());

		// Act
		List<Budget> result = budgetService.getAllBudgets();

		// Assert
		assertEquals(0, result.size());
		verify(budgetRepo, times(1)).findAll();
	}

	@Test
	public void testGetAllByUser_UserHasBudgets() {
		// Arrange
		List<Budget> expectedBudgets = Arrays.asList(new Budget(mockDate1, mockDate2, 500, mockUser, mockCategory),
				new Budget(mockDate1, mockDate2, 600, mockUser, mockCategory),
				new Budget(mockDate1, mockDate2, 700, mockUser, mockCategory));
		when(budgetRepo.findByUser(mockUser)).thenReturn(expectedBudgets);

		// Act
		List<Budget> result = budgetService.getAllByUser(mockUser);

		// Assert
		assertEquals(expectedBudgets.size(), result.size());
		assertEquals(expectedBudgets, result);
		verify(budgetRepo, times(1)).findByUser(mockUser);
	}

	@Test
	public void testGetAllByUser_UserHasNoBudgets() {
		// Arrange
		when(budgetRepo.findByUser(mockUser)).thenReturn(Collections.emptyList());

		// Act
		List<Budget> result = budgetService.getAllByUser(mockUser);

		// Assert
		assertEquals(0, result.size());
		verify(budgetRepo, times(1)).findByUser(mockUser);
	}

	@Test
	public void testGetBudgetsByMonthAndYear_BudgetsFound() {
		// Arrange
		int month = 4; // April
		int year = 2024;
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.plusMonths(1).minusDays(1);
		List<Budget> expectedBudgets = Arrays.asList(new Budget(mockDate1, mockDate2, 600, mockUser, mockCategory),
				new Budget(mockDate1, mockDate2, 500, mockUser, mockCategory),
				new Budget(mockDate1, mockDate2, 400, mockUser, mockCategory));
		when(budgetRepo.findByStartDateBetween(startDate, endDate)).thenReturn(expectedBudgets);

		// Act
		List<Budget> result = budgetService.getBudgetsByMonthAndYear(month, year);

		// Assert
		assertEquals(expectedBudgets.size(), result.size());
		assertEquals(expectedBudgets, result);
		verify(budgetRepo, times(1)).findByStartDateBetween(startDate, endDate);
	}

	@Test
	public void testGetBudgetsByMonthAndYear_NoBudgetsFound() {
		// Arrange
		int month = 2; // February
		int year = 2023;
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.plusMonths(1).minusDays(1);
		when(budgetRepo.findByStartDateBetween(startDate, endDate)).thenReturn(Collections.emptyList());

		// Act
		List<Budget> result = budgetService.getBudgetsByMonthAndYear(month, year);

		// Assert
		assertEquals(0, result.size());
		verify(budgetRepo, times(1)).findByStartDateBetween(startDate, endDate);
	}

	@Test
	public void testFindBudgetByCategoryAndDate_BudgetsFound() {
		// Arrange
		LocalDate date = LocalDate.of(2024, 4, 15);
		List<Budget> expectedBudgets = Arrays.asList(
				new Budget(LocalDate.of(2024, 4, 10), LocalDate.of(2024, 4, 20), 1000, mockUser, mockCategory),
				new Budget(LocalDate.of(2024, 4, 5), LocalDate.of(2024, 4, 25), 2000, mockUser, mockCategory));
		when(budgetRepo.findByCategoryCategoryIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
				mockCategory.getCategoryID(), date, date)).thenReturn(expectedBudgets);

		// Act
		List<Budget> result = budgetService.findBudgetByCategoryAndDate(mockCategory, date);

		// Assert
		assertEquals(expectedBudgets.size(), result.size());
		assertEquals(expectedBudgets, result);
		verify(budgetRepo, times(1)).findByCategoryCategoryIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
				mockCategory.getCategoryID(), date, date);
	}

	@Test
	public void testFindBudgetByCategoryAndDate_NoBudgetsFound() {
		// Arrange
		LocalDate date = LocalDate.of(2024, 4, 15);
		when(budgetRepo.findByCategoryCategoryIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
				mockCategory.getCategoryID(), date, date)).thenReturn(Collections.emptyList());

		// Act
		List<Budget> result = budgetService.findBudgetByCategoryAndDate(mockCategory, date);

		// Assert
		assertEquals(0, result.size());
		verify(budgetRepo, times(1)).findByCategoryCategoryIDAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
				mockCategory.getCategoryID(), date, date);
	}

	@Test
	public void testGetBudgetsByUserByMonthAndYear_UserHasBudgetsInMonthAndYear() {
		// Arrange
		int month = 4; // April
		int year = 2024;

		Budget budget1 = new Budget(LocalDate.of(2024, 4, 5), LocalDate.of(2024, 4, 20), 500, mockUser, mockCategory);
		Budget budget2 = new Budget(LocalDate.of(2024, 4, 10), LocalDate.of(2024, 4, 25), 500, mockUser, mockCategory);

		when(budgetRepo.findByStartDateBetween(any(LocalDate.class), any(LocalDate.class)))
				.thenReturn(Arrays.asList(budget1, budget2));

		// Act
		List<Budget> result = budgetService.getBudgetsByUserByMonthAndYear(month, year, mockUser);

		// Assert
		assertEquals(2, result.size());
		verify(budgetRepo, times(1)).findByStartDateBetween(any(LocalDate.class), any(LocalDate.class));
	}

	@Test
	public void testGetBudgetsByUserByMonthAndYear_UserHasNoBudgetsInMonthAndYear() {
		// Arrange
		int month = 5; // May
		int year = 2024;

		when(budgetRepo.findByStartDateBetween(any(LocalDate.class), any(LocalDate.class)))
				.thenReturn(Collections.emptyList());

		// Act
		List<Budget> result = budgetService.getBudgetsByUserByMonthAndYear(month, year, mockUser);

		// Assert
		assertEquals(0, result.size());
		verify(budgetRepo, times(1)).findByStartDateBetween(any(LocalDate.class), any(LocalDate.class));
	}

	@Test
	public void testDeleteBudgetById() {
		// Arrange
		int budgetId = 1;

		// Act
		budgetService.deleteBudgetById(budgetId);

		// Assert
		verify(budgetRepo, times(1)).deleteById(budgetId);
	}
}
