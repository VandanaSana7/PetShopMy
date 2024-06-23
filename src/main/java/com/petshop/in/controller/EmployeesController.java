package com.petshop.in.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNameNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNotFoundException;
import com.petshop.in.exceptions.employees.EmployeePositionNotFoundException;
import com.petshop.in.model.Employees;
import com.petshop.in.serviceimpl.EmployeesServiceImpl;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {
	
	@Autowired
	EmployeesServiceImpl employeesService;
	
	@GetMapping
	public ResponseEntity<List<Employees>> getAllEmployees() throws EmployeeNotFoundException
	{
		return new ResponseEntity<>(employeesService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/{employee_id}")
	public ResponseEntity<Employees> getEmployeesByEmployeeId(@PathVariable("employee_id") int employee_id) throws EmployeeIdNotFoundException
	{
		return new ResponseEntity<>(employeesService.getEmployeesByEmployeeId(employee_id), HttpStatus.OK);
	}
	
//	@GetMapping("/name/{first_name}")
//	public ResponseEntity<Employees> getEmployeesByFirstName(@PathVariable("first_name") String first_name)
//	{
//		return new ResponseEntity<>(employeesService.getEmployeesByFirstName(first_name), HttpStatus.OK);
//	}
//	
//	@GetMapping("/name/{last_name}")
//	public ResponseEntity<Optional<Employees>> getEmployeesByLastName(@PathVariable("last_name") String last_name)
//	{
//		return new ResponseEntity<>(employeesService.getEmployeesByLastName(last_name), HttpStatus.OK);
//	}
	
	@GetMapping("/name/{first_name}/{last_name}")
	public ResponseEntity<Employees> getEmployeesByFirstNameAndLastName(@PathVariable("first_name") String first_name, @PathVariable("last_name") String last_name) throws EmployeeNameNotFoundException
	{
		return new ResponseEntity<>(employeesService.getEmployeesByEmployeeName(first_name, last_name), HttpStatus.OK);
	}
	
	@GetMapping("/position/{position_name}")
	public ResponseEntity<List<Employees>> getEmployeesByPosition(@PathVariable("position_name") String position_name) throws EmployeePositionNotFoundException
	{
		return new ResponseEntity<>(employeesService.getAllEmployeesByPosition(position_name), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addEmployees(@RequestBody Employees employee) throws EmployeeCannotBeAddedException
	{
		return new ResponseEntity<>(employeesService.addEmployees(employee), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{employee_id}")
	public ResponseEntity<SuccessResponse> updateEmployeesByEmployeeId(@PathVariable("employee_id") int employee_id, @RequestBody Employees employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException
	{
		return new ResponseEntity<>(employeesService.updateEmployeesByEmployeeId(employee_id, employee), HttpStatus.OK);
	}

}
