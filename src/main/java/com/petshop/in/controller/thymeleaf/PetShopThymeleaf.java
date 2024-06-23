package com.petshop.in.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PetShopThymeleaf {

	@GetMapping("/signup")
	public String showSignUpPage()
	{
		return "SignUp";
	}
	
	@GetMapping("/main")
	public String showMainPage()
	{
		return "MainPage";
	}
	
	@GetMapping("/serviceshome")
	public String showServicesHomePage()
	{
		return "ServicesHomePage";
	}
	
	@GetMapping("/petshome")
	public String showPetsHomePage()
	{
		return "PetsHomePage";
	}
	
	@GetMapping("/transactionshome")
	public String showTransactionsHomePage()
	{
		return "TransactionsHomePage";
	}
}
