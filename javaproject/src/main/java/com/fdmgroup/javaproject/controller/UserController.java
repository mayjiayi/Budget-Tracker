package com.fdmgroup.javaproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/") 
	public String slash() {
		return ("register");
	}
	
	@GetMapping("/register") 
	public String register() {
		return ("register");
	}
	
	@PostMapping("/register")
	public String processRegistration(	@RequestParam String username, 
										@RequestParam String password, 
										@RequestParam String email,
										@RequestParam String firstName, 
										@RequestParam String lastName
										) {
		System.out.println("User registration processing...");
		System.out.println("Username : " + username + " | Password : " + password + " | Email : " + email);
		User newUser = new User(username, password, email, firstName, lastName);
		if (userService.registerNewUser(newUser)) {
			return("redirect:/login");
		} else {
			return("register");
		}
	}
	
	@GetMapping("/login") 
	public String login() {
		return ("login");
	}
	
	@PostMapping("/login")
	public String processLogin(	@RequestParam String username, 
            					@RequestParam String password, 
            					HttpSession session) {
		Optional<User> targetUser = userService.validatePassword(username, password);
    	if (targetUser.isEmpty()) {
    		System.out.println("Authentication failed.");
    		return "login";
    	} else {
    		System.out.println("Authentication was successful!");
    		User foundUser = userService.findUser(username);
    		int currentUserId = foundUser.getUserID();
    		session.setAttribute("user", foundUser);
    		session.setMaxInactiveInterval(1800);
    		return "redirect:/dashboard/" + currentUserId;
    		
    	}
	}
	
	@GetMapping("/dashboard/{id}") 
	public String dashboard(@PathVariable("id") long userid, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		
		if ( user != null) {
            // User is logged in, show dashboard
			model.addAttribute("user", user);
            return "dashboard";
        } else {
            // User is not logged in, redirect to login page
            return "redirect:/login";
        }
	}
	

	

}
