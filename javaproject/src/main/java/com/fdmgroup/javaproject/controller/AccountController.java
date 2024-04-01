package com.fdmgroup.javaproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.javaproject.model.Account;
import com.fdmgroup.javaproject.model.User;
import com.fdmgroup.javaproject.service.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/dashboard/accounts") 
	public String accounts(Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		List<Account> accounts = accountService.getAllByUser(user);
		model.addAttribute("accounts", accounts);
		return ("accounts");
	}
	
	@PostMapping("/dashboard/accounts")
	public String createAccount(@RequestParam String accountName, 
								@RequestParam double balance, 
								HttpSession session) {
		System.out.println("Creating account...");
		System.out.println("Account Name : " + accountName + " | Balance : " + balance);
		
		User user = (User) session.getAttribute("user");
		
		Account newAccount = new Account(accountName, balance, user);
		System.out.println("Account Name : " + accountName + " | Balance : " + balance + " | User : " + user.getUserID());
		
		if (accountService.createNewAccount(newAccount)) {
			return("redirect:/dashboard/accounts");
		} else {
			return("redirect:/dashboard/accounts");
		}
	}
}
