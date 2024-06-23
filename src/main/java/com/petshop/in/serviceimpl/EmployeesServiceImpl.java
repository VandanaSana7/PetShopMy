package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
//import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNameNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNotFoundException;
import com.petshop.in.exceptions.employees.EmployeePositionNotFoundException;
import com.petshop.in.model.Employees;
import com.petshop.in.repository.EmployeesRepository;
import com.petshop.in.service.EmployeesService;

@Service
public class EmployeesServiceImpl implements EmployeesService{

	@Autowired
	EmployeesRepository employeesRepo;
	
	@Override
	public List<Employees> getAllEmployees() throws EmployeeNotFoundException{
		List<Employees> employees = employeesRepo.findAll();
		if(employees.isEmpty())
		{
			throw new EmployeeNotFoundException("Validation failed: No employees found");
		}
		return employees;
	}

	@Override
	public Employees getEmployeesByEmployeeId(int employee_id) throws EmployeeIdNotFoundException {
		
		try
		{
			Employees employeeId = employeesRepo.findById(employee_id).get();
			return employeeId;
		}
		catch(NoSuchElementException e)
		{
			throw new EmployeeIdNotFoundException("Validation failed: No employee with this Id found");
		}
		
	}

//	public Employees getEmployeesByFirstName(String first_name) {
//		
//		Employees employeeName = employeesRepo.findByFirstName(first_name);
//		return employeeName;
//	}
//	
//	public Optional<Employees> getEmployeesByLastName(String last_name) {
//		
//		return employeesRepo.findByLastName(last_name);
//	}
	
	
	@Override
	public Employees getEmployeesByEmployeeName(String first_name, String last_name) throws EmployeeNameNotFoundException {
		
		Employees employeeName = employeesRepo.findByFirstNameAndLastName(first_name, last_name);
		if(employeeName == null)
		{
			throw new EmployeeNameNotFoundException("Validation failed: No employee with this name is found");
		}
		return employeeName;
	}

	@Override
	public List<Employees> getAllEmployeesByPosition(String position_name) throws EmployeePositionNotFoundException {
		
		List<Employees> employeePosition = employeesRepo.findByPosition(position_name);
		if(employeePosition.isEmpty())
		{
			throw new EmployeePositionNotFoundException("Validation failed: No employee found with this position");
		}
		return employeePosition;
	}

	@Override
	public SuccessResponse addEmployees(Employees employee) throws EmployeeCannotBeAddedException{
		
		Employees emp = employeesRepo.save(employee);
		if(!ValidationClass.validationInt(emp.getFirstName()))
		{
			throw new EmployeeCannotBeAddedException("Validation failed: First name should be string");
		}
		if(!ValidationClass.validationInt(emp.getLastName()))
		{
			throw new EmployeeCannotBeAddedException("Validation failed: Last name should be string");
		}
		if(!ValidationClass.validationInt(emp.getPosition()))
		{
			throw new EmployeeCannotBeAddedException("Validation failed: Position should be string");
		}
		if(!ValidationClass.validationInt(emp.getPhoneNumber()))
		{
			throw new EmployeeCannotBeAddedException("Validation failed: Phone number should be int");
		}
		if(!ValidationClass.validationDate(emp.getHireDate().toString()))
		{
			throw new EmployeeCannotBeAddedException("Validation failed: Date is not valid");
		}
		
		SuccessResponse s = new SuccessResponse();
		s.setMessage("Data added successfully");
		s.setStatus(HttpStatus.ACCEPTED.toString());
		s.setTimeStamp(LocalDate.now());
		return s;
	}

	@Override
	public SuccessResponse updateEmployeesByEmployeeId(int employee_id, Employees employee) throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException {
		
		Employees existingEmployee = employeesRepo.findById(employee_id).get();
		if(existingEmployee == null)
		{
			throw new EmployeeIdNotFoundException("Validation failed: Id not found");
		}
		if(!ValidationClass.validationInt(employee.getFirstName()))
		{
			throw new EmployeeCannotBeUpdatedException("Validation failed: First name should be string");
		}
		if(!ValidationClass.validationInt(employee.getLastName()))
		{
			throw new EmployeeCannotBeUpdatedException("Validation failed: Last name should be string");
		}
		if(!ValidationClass.validationInt(employee.getPosition()))
		{
			throw new EmployeeCannotBeUpdatedException("Validation failed: Position should be string");
		}
		if(!ValidationClass.validationInt(employee.getPhoneNumber()))
		{
			throw new EmployeeCannotBeUpdatedException("Validation failed: Phone number should be int");
		}
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setPhoneNumber(employee.getPhoneNumber());
		existingEmployee.setPosition(employee.getPosition());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setHireDate(employee.getHireDate());
		existingEmployee.setAddress(employee.getAddress());
		
		SuccessResponse s = new SuccessResponse();
		s.setMessage("Data updated successfully");
		s.setStatus(HttpStatus.ACCEPTED.toString());
		s.setTimeStamp(LocalDate.now());
		return s;
	}

}
