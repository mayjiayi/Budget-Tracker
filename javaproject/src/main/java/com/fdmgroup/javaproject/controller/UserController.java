package com.fdmgroup.javaproject.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.UserService;

import jakarta.servlet.http.HttpSession;

/**
 * UserController is responsible for returning html templates for anything user
 * entity related
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@GetMapping("/")
	public String slash() {
		return ("index");
	}

	@GetMapping("/register")
	public String register() {
		return ("register");
	}

	@PostMapping("/register")
	public String processRegistration(@RequestParam String username, @RequestParam String password,
			@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName) {
		logger.info("Registering User '" + username + "' ...");
		logger.info("Username : " + username + " | Password : " + password + " | Email : " + email);

		User newUser = new User(username, password, email, firstName, lastName);
		if (userService.registerNewUser(newUser)) {
			logger.info("User '" + username + "' registered.");
			return ("redirect:/login");
		} else {
			logger.warn("Unable to register user '" + username + "'.");
			return ("register");
		}
	}

	@GetMapping("/login")
	public String login() {
		return ("login");
	}

	@PostMapping("/login")
	public String processLogin(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		Optional<User> targetUser = userService.validatePassword(username, password);
		if (targetUser.isEmpty()) {
			logger.warn("Authentication failed for User '" + username + "'.");
			model.addAttribute("error", true);
			return "login";
		} else {
			logger.info("Authentication was successful for User '" + username + "'.");

			User foundUser = userService.findUser(username);
			int currentUserId = foundUser.getUserID();

			session.setAttribute("user", foundUser);
			session.setMaxInactiveInterval(1800);

			logger.info("Current User " + username + " added to session attribute.");

			return "redirect:/dashboard/" + currentUserId;
		}
	}

}
