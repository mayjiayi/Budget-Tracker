package com.fdmgroup.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.javaproject.model.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer>{

}
