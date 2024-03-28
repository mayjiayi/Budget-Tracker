package com.fdmgroup.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.javaproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
