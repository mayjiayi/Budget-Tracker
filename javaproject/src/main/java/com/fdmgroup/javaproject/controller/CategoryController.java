package com.fdmgroup.javaproject.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.repository.CategoryRepository;
import com.fdmgroup.javaproject.service.CategoryService;

import jakarta.annotation.PostConstruct;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostConstruct
	public void init() {
		List<String> categories = Arrays.asList("Food/Drinks",
												"Shopping", 
												"Transportation",
												"Entertainment",
												"Home",
												"Pets",
												"Travel",
												"Other(Expenses)",
												"Income",
												"Other(Income)");
		for(String category : categories) {
			categoryService.createCategory(category);
		}
    }
}
