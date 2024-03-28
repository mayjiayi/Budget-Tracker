package com.fdmgroup.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.javaproject.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
