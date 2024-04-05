package com.fdmgroup.javaproject.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdmgroup.javaproject.service.CategoryService;

import jakarta.annotation.PostConstruct;

/**
 * CategoryController is responsible for initializing a list of pre-set
 * categories for ease of data compilation
 * 
 */
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	private static final Logger logger = LogManager.getLogger(CategoryController.class);

	@PostConstruct
	public void init() {
		List<String> categories = Arrays.asList("Food/Drinks", "Shopping", "Transportation", "Entertainment", "Home",
				"Pets", "Travel", "Other(Expenses)", "Income", "Other(Income)");
		for (String category : categories) {
			categoryService.createCategory(category);
		}
		logger.info("All categories present in database.");
	}
}
