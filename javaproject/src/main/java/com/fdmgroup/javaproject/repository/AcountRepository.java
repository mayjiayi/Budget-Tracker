package com.fdmgroup.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.javaproject.model.Account;


public interface AcountRepository extends JpaRepository<Account, Integer>{

}
