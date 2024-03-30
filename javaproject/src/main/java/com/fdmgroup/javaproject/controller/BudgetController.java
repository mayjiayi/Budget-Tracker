package com.fdmgroup.javaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fdmgroup.javaproject.service.BudgetService;

@Controller
public class BudgetController {
	
	@Autowired
	private BudgetService budgetService;
	
	

}
