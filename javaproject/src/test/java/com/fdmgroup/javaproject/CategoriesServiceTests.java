package com.fdmgroup.javaproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.repository.CategoryRepository;
import com.fdmgroup.javaproject.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoriesServiceTests {

	@Mock
	private CategoryRepository categoryRepo;

	@InjectMocks
	private CategoryService categoryService;

	@Test
	public void testCreateCategory_SuccessfulCreation() {
		// Arrange
		String categoryName = "Test Category";
		when(categoryRepo.findByCategoryName(categoryName)).thenReturn(Collections.emptyList());

		// Act
		boolean result = categoryService.createCategory(categoryName);

		// Assert
		assertTrue(result);
		verify(categoryRepo, times(1)).findByCategoryName(categoryName);
		verify(categoryRepo, times(1)).save(any(Category.class));
	}

	@Test
	public void testCreateCategory_CategoryAlreadyExists() {
		// Arrange
		String categoryName = "Existing Category";
		List<Category> existingCategories = List.of(new Category(categoryName));
		when(categoryRepo.findByCategoryName(categoryName)).thenReturn(existingCategories);

		// Act
		boolean result = categoryService.createCategory(categoryName);

		// Assert
		assertFalse(result);
		verify(categoryRepo, times(1)).findByCategoryName(categoryName);
		verify(categoryRepo, never()).save(any(Category.class));
	}

	@Test
	public void testGetAllCategories_CategoriesFound() {
		// Arrange
		List<Category> categoryList = List.of(new Category("Category 1"), new Category("Category 2"),
				new Category("Category 3"));
		when(categoryRepo.findAll()).thenReturn(categoryList);

		// Act
		List<Category> result = categoryService.getAllCategories();

		// Assert
		assertEquals(categoryList.size(), result.size());
		assertEquals(categoryList, result);
		verify(categoryRepo, times(1)).findAll();
	}

	@Test
	public void testGetAllCategories_NoCategoriesFound() {
		// Arrange
		when(categoryRepo.findAll()).thenReturn(Collections.emptyList());

		// Act
		List<Category> result = categoryService.getAllCategories();

		// Assert
		assertTrue(result.isEmpty());
		verify(categoryRepo, times(1)).findAll();
	}

	@Test
	public void testFindById_CategoryFound() {
		// Arrange
		int categoryId = 1;
		Category expectedCategory = new Category("Test Category");
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

		// Act
		Category result = categoryService.findById(categoryId);

		// Assert
		assertNotNull(result);
		assertEquals(expectedCategory, result);
		verify(categoryRepo, times(1)).findById(categoryId);
	}

	@Test
	public void testFindById_CategoryNotFound() {
		// Arrange
		int categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.empty());

		// Act
		Category result = categoryService.findById(categoryId);

		// Assert
		assertNull(result);
		verify(categoryRepo, times(1)).findById(categoryId);
	}
}
