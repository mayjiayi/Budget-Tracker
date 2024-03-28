package com.fdmgroup.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.javaproject.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
