package com.fdmgroup.javaproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.Category;
import com.fdmgroup.javaproject.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public boolean createCategory(String categoryName) {
		List<Category> categoryList = categoryRepo.findByCategoryName(categoryName);
		if(categoryList.isEmpty()) {
			Category newCategory = new Category(categoryName);
			categoryRepo.save(newCategory);
			return true;
		} else {
			System.out.println("Category '" + categoryName + "' already exists.");
			return false;
			
		}
		
	}
}
