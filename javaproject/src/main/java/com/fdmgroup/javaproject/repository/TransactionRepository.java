package com.fdmgroup.javaproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.javaproject.model.Transaction;
import com.fdmgroup.javaproject.model.User;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
	
	public List<Transaction> findByUser(User user);
	@Query("SELECT t FROM Transaction t WHERE t.user = :user AND t.date BETWEEN :startDate AND :endDate AND t.type = :type")
    List<Transaction> findByUserAndDateBetweenAndType(
            @Param("user") User user,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("type") String type);
	
	@Query("SELECT t FROM Transaction t WHERE t.user = :user AND YEAR(t.date) = :year AND MONTH(t.date) = :month")
    List<Transaction> findByUserAndMonthAndYear(User user, int month, int year);
}
