package com.fdmgroup.javaproject.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.repository.UserRepository;

/**
 * UserService is responsible for all methods related to manipulating the User entity in the database.
 * 
 * @author May Lwin
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	/**
	 * Creates User entities to be saved into the database
	 * 
	 * @param user
	 * @return true is user has been created, false if user already exists in database
	 */
	public boolean registerNewUser(User user) {
		Optional<User> returnedUser = userRepo.findByUsername(user.getUsername());
		
		if (returnedUser.isEmpty()) {
			userRepo.save(user);
			return true;
		} else {
			logger.info("User " + user.getUsername() + " already exists in database.");
			return false;
		}
	}
	
	/**
	 * Retrieves User saved in database by using userId
	 * 
	 * @param userId
	 * @return user of the argument userId in the database
	 */
	public User findUser(int userId) {
		Optional<User> foundUser = userRepo.findById(userId);
		
		return foundUser.orElse(new User("defaultusername", "defaultpassword", "defaultEmail", "defaultfirstName", "defaultlastName"));
	}
	
	/**
	 * Retrieves User from database by using username
	 * 
	 * @param username
	 * @return user of argument username in database
	 */
	public User findUser(String username) {
		Optional<User> foundUser = userRepo.findByUsername(username);
		
		return foundUser.orElse(new User("defaultusername", "defaultpassword", "defaultEmail", "defaultfirstName", "defaultlastName"));
	}
	
	/**
	 * Checks if user login is valid
	 * 
	 * @param username
	 * @param password
	 * @return validated User from the database
	 */
	public Optional<User> validatePassword(String username, String password) {
		
		Optional<User> targetUser = userRepo.findByUsername(username);
		
		if (targetUser.isEmpty()) {
			logger.info("User " + username + " does not exists in the database.");
			return Optional.empty();
		} else {
			User user = targetUser.get();
			if (user.getPassword().equals(password)) {
				return targetUser;
			} else {
				logger.info("Mismatch between password and username for '" + username + "'");
				return Optional.empty();
			}
		}
	}
}
