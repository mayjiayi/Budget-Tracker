package com.fdmgroup.javaproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public boolean registerNewUser(User user) {
		Optional<User> returnedUser = userRepo.findByUsername(user.getUserName());
		
		if (returnedUser.isEmpty()) {
			userRepo.save(user);
			return true;
		} else {
			System.out.println("User already exists.");
			return false;
		}
	}
	
	public User findUser(int userId) {
		Optional<User> foundUser = userRepo.findById(userId);
		
		return foundUser.orElse(new User("defaultusername", "defaultpassword", "defaultEmail"));
	}
	
	public Optional<User> validatePassword(String username, String password) {
		
		Optional<User> targetUser = userRepo.findByUsername(username);
		
		if (targetUser.isEmpty()) {
			return Optional.empty();
		} else {
			User user = targetUser.get();
			if (user.getPassword().equals(password)) {
				return targetUser;
			} else {
				return Optional.empty();
			}
		}
	}
}
