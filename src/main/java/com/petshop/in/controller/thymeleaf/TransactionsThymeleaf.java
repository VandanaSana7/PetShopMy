package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.transactions.FailedTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.SuccessTransactionNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionCannotBeAddedException;
import com.petshop.in.exceptions.transactions.TransactionIdNotFoundException;
import com.petshop.in.exceptions.transactions.TransactionsNotFoundException;
import com.petshop.in.model.Transactions;
import com.petshop.in.service.TransactionsService;

@Controller
@RequestMapping("/transactions")
public class TransactionsThymeleaf {
	
	@Autowired
	private TransactionsService transactionService;
	
	@GetMapping("/getAllTransactions")
	public String getAllTransactions(Model model) throws TransactionsNotFoundException 
	{
		List<Transactions> transactionsList = transactionService.getAllTransactions();
		model.addAttribute("AllTransactions", transactionsList);
		return "TransactionsList";
	}
	
	@GetMapping("/form")
	public String showTransactionIdForm() {
		return "TransactionIdForm";
	}

	@GetMapping("/getTransactionId/transactionId")
	public String getTransactionById(@RequestParam("transactionId") Integer transactionId, Model model) throws TransactionIdNotFoundException
	{
		Transactions transaction = transactionService.getTransactionsByTransactionId(transactionId);
		model.addAttribute("TransactionById", transaction);
		return "TransactionById";
	}
	
	@GetMapping("/customeridform")
	public String showTransactionByCustomerIdForm() {
		return "TransactionByCustomerIdForm";
	}

	@GetMapping("/getTransaction/customerId")
	public String getTransactionByCustomerId(@RequestParam("customerId") Integer customerId, Model model) throws CustomerIdNotFoundException 
	{
		Transactions transaction = transactionService.getTransactionsByCustomerId(customerId);
		model.addAttribute("TransactionByCustomerId", transaction);
		return "TransactionByCustomerId";
	}
	
	@GetMapping("/getAllSuccessfulTransactions")
	public String getAllSuccessfulTransactions(Model model) throws SuccessTransactionNotFoundException, TransactionsNotFoundException 
	{
		List<Transactions> transactionsList = transactionService.getAllSuccessfulTransactions();
		model.addAttribute("AllSuccessfulTransactions", transactionsList);
		return "SuccessfulTransactionsList";
	}
	
	@GetMapping("/getAllFailedTransactions")
	public String getAllFailedTransactions(Model model) throws FailedTransactionNotFoundException, TransactionsNotFoundException 
	{
		List<Transactions> transactionsList = transactionService.getAllFailedTransactions();
		model.addAttribute("AllFailedTransactions", transactionsList);
		return "FailedTransactionsList";
	}
	
	@GetMapping("/posttransactionform")
	public String showTransactionForm()
	{
		return "PostTransactionForm";
	}
	
	@PostMapping("/add")
	public String addTransaction(Model model, @ModelAttribute("transaction") Transactions transaction) throws TransactionCannotBeAddedException, MismatchDataTypeException
	{
		SuccessResponse s = transactionService.addTransactions(transaction);
		model.addAttribute("AddTransaction", s);
		return "PostAddTransaction";
	}
	
	
}
