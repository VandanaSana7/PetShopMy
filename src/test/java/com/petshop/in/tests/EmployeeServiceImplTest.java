package com.petshop.in.tests;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.employees.EmployeeCannotBeAddedException;
import com.petshop.in.exceptions.employees.EmployeeCannotBeUpdatedException;
import com.petshop.in.exceptions.employees.EmployeeIdNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNameNotFoundException;
import com.petshop.in.exceptions.employees.EmployeeNotFoundException;
import com.petshop.in.exceptions.employees.EmployeePositionNotFoundException;
import com.petshop.in.model.Employees;
import com.petshop.in.repository.EmployeesRepository;
import com.petshop.in.serviceimpl.EmployeesServiceImpl;

 
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
 
    @Mock
    private EmployeesRepository employeeRepository;
 
    @InjectMocks
    private EmployeesServiceImpl employeeService;
 
    private Employees testEmployee;
 
    @BeforeEach
    public void setUp() {

        // Create a test employee

        testEmployee = new Employees(1, "John", "Doe", "Manager", new Date(), "1234567890", "john.doe@example.com", null);

    }
 
    @Test
    public void testGetEmployeesByName() throws EmployeeNameNotFoundException {

        // Mock the behavior of the repository
        when(employeeRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(testEmployee);
        // Call the service method
        Employees result = employeeService.getEmployeesByEmployeeName("John", "Doe");
        // Verify the result
        assertEquals(testEmployee, result);

    }
 
    @Test
    public void testGetEmployeesByName_NotFound() {

        // Mock the behavior of the repository
        when(employeeRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(null);
        // Use assertThrows to verify that the method throws the expected exception
        assertThrows(EmployeeNameNotFoundException.class, () -> {

            // Call the service method

            employeeService.getEmployeesByEmployeeName("John", "Doe");

        });

    }
 
    @Test
    public void testGetEmployeesByPosition() throws EmployeePositionNotFoundException {

        // Mock the behavior of the repository

        List<Employees> employees = new ArrayList<>();

        employees.add(testEmployee);

        when(employeeRepository.findByPosition("Manager")).thenReturn(employees);
 
        // Call the service method

        List<Employees> result = employeeService.getAllEmployeesByPosition("Manager");
 
        // Verify the result

        assertEquals(1, result.size());

        assertEquals(testEmployee, result.get(0));

    }
 
    

    @Test
    public void testGetEmployeesByPosition_NotFound() {

        // Mock the behavior of the repository

        when(employeeRepository.findByPosition("Manager")).thenReturn(new ArrayList<>());
 
        // Use assertThrows to verify that the method throws the expected exception

        assertThrows(EmployeePositionNotFoundException.class, () -> {

            // Call the service method

            employeeService.getAllEmployeesByPosition("Manager");

        });

    }

//    @Test
//    public void testGetEmployeesById() throws EmployeeIdNotFoundException {
//
//        // Mock the behavior of the repository
//
//        when(employeeRepository.findById(1)).thenReturn(testEmployee);
// 
//        // Call the service method
//
//        Employees result = employeeService.getEmployeesById(1);
// 
//        // Verify the result
//
//        assertEquals(testEmployee, result);
//
//    }

 
//    @Test
//    public void testGetEmployeesById_NotFound() {
//
//        // Mock the behavior of the repository
//
//        when(employeeRepository.findById(1)).thenReturn(null);
// 
//        // Use assertThrows to verify that the method throws the expected exception
//
//        assertThrows(EmployeeIdNotFoundException.class, () -> {
//
//            // Call the service method
//
//            employeeService.getEmployeesByEmployeeId(1);
//
//        });
//
//    }
// 
    


    @Test
    public void testGetAllEmployees() throws EmployeeNotFoundException {

        // Mock the behavior of the repository

        List<Employees> employees = new ArrayList<>();

        employees.add(testEmployee);

        when(employeeRepository.findAll()).thenReturn(employees);
 
        // Call the service method

        List<Employees> result = employeeService.getAllEmployees();
 
        // Verify the result

        assertEquals(1, result.size());

        assertEquals(testEmployee, result.get(0));

    }
 
    

    @Test

    public void testGetAllEmployees_NoEmployeesFound() {

        // Mock the behavior of the repository

        when(employeeRepository.findAll()).thenReturn(new ArrayList<>());
 
        // Use assertThrows to verify that the method throws the expected exception

        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getAllEmployees();

        });
 
    }
 
    


//    @Test

//    public void testAddEmployee() throws EmployeeCannotBeAddedException {

//        // Mock the behavior of the repository

//        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);

//

//        // Call the service method

//        SuccessResponse result = employeeService.addEmployee(testEmployee);

//

//        // Verify the result

//        assertTrue(result.getMessage().contains("Data added"));

//    }


 
    @Test
    public void testAddEmployee_ValidationFailed() {

        // Mock the behavior of the repository

        when(employeeRepository.save(testEmployee)).thenReturn(testEmployee);
 
        // Use assertThrows to verify that the method throws the expected exception

        EmployeeCannotBeAddedException exception = assertThrows(EmployeeCannotBeAddedException.class, () -> {

            // Call the service method with invalid data

            testEmployee.setFirstName("123"); // Set an invalid first name

            employeeService.addEmployees(testEmployee);

        });
 
    }


 
//    @Test
//    public void testUpdateEmployee() throws EmployeeIdNotFoundException, EmployeeCannotBeUpdatedException {
//
//        // Mock the behavior of the repository
//
//        when(employeeRepository.findById(1)).thenReturn(testEmployee);
// 
//        // Call the service method
//
//        SuccessResponse result = employeeService.updateEmployeesByEmployeeId(1, testEmployee);
// 
//        // Verify the result
//
//        assertTrue(result.getMessage().contains("Data updated"));
//
//    }


 
//    @Test
//    public void testUpdateEmployee_IdNotFound() {
//
//        // Mock the behavior of the repository
//
//        when(employeeRepository.findById(1)).thenReturn(null);
// 
//        // Use assertThrows to verify that the method throws the expected exception
//
//        EmployeeIdNotFoundException exception = assertThrows(EmployeeIdNotFoundException.class, () -> {
//
//            employeeService.updateEmployeesByEmployeeId(1, testEmployee);
//
//        });
//
//    }


//    @Test
//    public void testUpdateEmployee_ValidationFailed() {
//
//        // Mock the behavior of the repository
//
//        when(employeeRepository.findById(1)).thenReturn(testEmployee);
// 
//        // Use assertThrows to verify that the method throws the expected exception
//
//        EmployeeCannotBeUpdatedException exception = assertThrows(EmployeeCannotBeUpdatedException.class, () -> {
//
//            // Set invalid data to cause validation failure
//
//            testEmployee.setFirstName("123");
//
//            employeeService.updateEmployeesByEmployeeId(1, testEmployee);
//
//        });
//    }
 
    

}

