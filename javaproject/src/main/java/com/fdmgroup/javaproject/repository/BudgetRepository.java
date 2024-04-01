package com.fdmgroup.javaproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.javaproject.model.Budget;
import com.fdmgroup.javaproject.model.User;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer>{
	
	public List<Budget> findByUser(User user);
}
