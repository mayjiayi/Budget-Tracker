package com.fdmgroup.javaproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	public List<Transaction> findByUser(User user);
}
