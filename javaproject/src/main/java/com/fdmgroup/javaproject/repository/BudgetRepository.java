package com.fdmgroup.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.javaproject.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer>{

}
