package com.fdmgroup.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.javaproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
