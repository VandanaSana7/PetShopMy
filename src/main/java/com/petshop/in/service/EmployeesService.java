package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNameNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNotFoundException;
import com.petshop.in.exceptions.employees.EmployeePositionNotFoundException;
import com.petshop.in.model.Employees;

public interface EmployeesService {

	List<Employees> getAllEmployees() throws EmployeeNotFoundException;
	Employees getEmployeesByEmployeeId(int employee_id) throws EmployeeIdNotFoundException;
	Employees getEmployeesByEmployeeName(String first_name, String last_name) throws EmployeeNameNotFoundException;
	List<Employees> getAllEmployeesByPosition(String position_name) throws EmployeePositionNotFoundException;
	SuccessResponse addEmployees(Employees employee) throws EmployeeCannotBeAddedException;
	SuccessResponse updateEmployeesByEmployeeId(int employee_id, Employees employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException;
	
}
