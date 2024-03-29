package com.fdmgroup.javaproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String processRegistration(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
		System.out.println("User registration processing...");
		System.out.println("Username : " + username + " | Password : " + password + " | Email : " + email);
		User newUser = new User(username, password, email);
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
    		session.setAttribute("currentUser", username);
    		return "redirect:/dashboard";
    		
    	}
	}
	
	@GetMapping("/dashboard") 
	public String dashboard(HttpSession session) {
		if (session.getAttribute("currentUser") != null) {
            // User is logged in, show dashboard
            return "dashboard";
        } else {
            // User is not logged in, redirect to login page
            return "redirect:/login";
        }
	}
	

	

}
