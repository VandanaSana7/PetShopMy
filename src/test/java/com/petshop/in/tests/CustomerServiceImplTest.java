package com.petshop.in.tests;

//public class CustomerServiceImplTest {
//
//}


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.customers.CustomerCannotBeAddedException;
import com.petshop.in.exceptions.customers.CustomerCannotBeUpdatedException;
import com.petshop.in.exceptions.customers.CustomerCityNotFoundException;
import com.petshop.in.exceptions.customers.CustomerFirstLastNameNotFoundException;
import com.petshop.in.exceptions.customers.CustomerIdNotFoundException;
import com.petshop.in.exceptions.customers.CustomerNotFoundException;
import com.petshop.in.exceptions.customers.CustomerStateNotFoundException;
import com.petshop.in.model.Customers;
import com.petshop.in.repository.CustomersRepository;
import com.petshop.in.repository.PetsRepository;
import com.petshop.in.repository.TransactionsRepository;
import com.petshop.in.serviceimpl.CustomersServiceImpl;

 
public class CustomerServiceImplTest {
 
    @Mock
    private CustomersRepository customerRepository;
 
    @Mock
    private TransactionsRepository transactionRepository;
 
    @Mock
    private PetsRepository petsRepository;
 
    @InjectMocks
    private CustomersServiceImpl customerService;
    
    @BeforeEach 
        public void setup() {
            MockitoAnnotations.openMocks(this);     
        }

        @Test
        public void testRetrieveAllCustomers() throws CustomerNotFoundException {
     
            List<Customers> customersList = new ArrayList<>();
     
            customersList.add(new Customers(1, "John", "Doe", "john@example.com", "1234567890", null));
     
            customersList.add(new Customers(2, "Jane", "Smith", "jane@example.com", "0987654321", null));

            when(customerRepository.findAll()).thenReturn(customersList);

            List<Customers> result = customerService.getAllCustomers();

            assertEquals(2, result.size());
     
        }

        @Test
        public void testGetCustomerById_ExistingId() throws CustomerIdNotFoundException  {
     
            Customers customer = new Customers(1, "John", "Doe", "john@example.com", "1234567890", null);
     
            Optional<Customers> optionalCustomer = Optional.of(customer);

            when(customerRepository.findById(1)).thenReturn(optionalCustomer);

            Customers result = customerService.getCustomerById(1);

            assertEquals(customer, result);
     
        }

        @Test 
        public void testGetCustomerById_NonExistingId() {
     
            when(customerRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(CustomerIdNotFoundException.class, () -> customerService.getCustomerById(1));
     
        }

        @Test
        public void testGetCustomerByCity_ExistingCity() throws CustomerCityNotFoundException {
     
            List<Customers> customersList = new ArrayList<>();
     
            customersList.add(new Customers(1, "John", "Doe", "john@example.com", "1234567890", null));

            when(customerRepository.findByCity("New York")).thenReturn(customersList);

            List<Customers> result = customerService.getCustomersByCity("New York");

            assertEquals(1, result.size());
     
        }

        @Test
        public void testGetCustomerByCity_NonExistingCity() {
     
            when(customerRepository.findByCity("New York")).thenReturn(new ArrayList<>());

            assertThrows(CustomerCityNotFoundException.class, () -> customerService.getCustomersByCity("New York"));
     
        }

        @Test 
        public void testGetCustomerByState_ExistingState() throws CustomerStateNotFoundException {
     
            List<Customers> customersList = new ArrayList<>();
     
            customersList.add(new Customers(1, "John", "Doe", "john@example.com", "1234567890", null));

            when(customerRepository.findByState("California")).thenReturn(customersList);

            List<Customers> result = customerService.getCustomersByState("California");

            assertEquals(1, result.size());
     
        }

        @Test 
        public void testGetCustomerByState_NonExistingState() {
     
            when(customerRepository.findByState("California")).thenReturn(new ArrayList<>());

            assertThrows(CustomerStateNotFoundException.class, () -> customerService.getCustomersByState("California"));
     
        }

//        @Test    
//        public void testGetCustomerByTransactionCustomerId_ExistingCustomerId() throws CustomerTransactionCustomerIdNotFoundException {
//     
//            List<Transactions> transactionsList = new ArrayList<>();
//     
//            transactionsList.add(new Transactions());
//
//            when(transactionRepository.findByCustomer_CustomerId(1)).thenReturn(transactionsList);
//
//            List<Transactions> result = customerService.getCustomerBytranscustid(1);
//
//            assertEquals(1, result.size());
//     
//        }
//
//        @Test
//     
//        public void testGetCustomerByTransactionCustomerId_NonExistingCustomerId() {
//     
//            when(transactionRepository.findByCustomer_CustomerId(1)).thenReturn(new ArrayList<>());
//
//            assertThrows(CustomerTransactionCustomerIdNotFoundException.class, () -> customerService.getCustomerBytranscustid(1));
//     
//        }
//
//        @Test
//     
//        public void testFindCustomersByTransactionStatus_ExistingStatus() throws CustomerTransactionStatusNotFoundException {
//     
//            List<Transactions> transactionsList = new ArrayList<>();
//     
//            transactionsList.add(new Transactions());
//
//            when(transactionRepository.findByTransactionStatus(transaction_status.Success)).thenReturn(transactionsList);
//
//            List<Transactions> result = customerService.findCustomersByTransactionStatus(transaction_status.Success);
//
//            assertEquals(1, result.size());
//     
//        }
//
//
//        @Test
//     
//        public void testFindCustomersByNotransaction() {
//     
//            List<Customers> customersList = new ArrayList<>();
//     
//            customersList.add(new Customers("John", "Doe", "john@example.com", "1234567890", null));
//
//            when(customerRepository.findCustomerByNotransaction()).thenReturn(customersList);
//
//            List<Customers> result = customerService.findCustomersByNotransaction();
//
//            assertEquals(1, result.size());
//     
//        }

        @Test  
        public void testGetCustomerByfirstNameAndLastName_ExistingName() throws CustomerFirstLastNameNotFoundException  {
     
            List<Customers> customersList = new ArrayList<>();
     
            customersList.add(new Customers(1, "John", "Doe", "john@example.com", "1234567890", null));

            when(customerRepository.findByFirstAndLastName("John", "Doe")).thenReturn(customersList);

            List<Customers> result = customerService.getCustomerByFirstAndLastName("John", "Doe");

            assertEquals(1, result.size());
     
        }

        @Test
        public void testGetCustomerByfirstNameAndLastName_NonExistingName() {
     
            when(customerRepository.findByFirstAndLastName("John", "Doe")).thenReturn(new ArrayList<>());

            assertThrows(CustomerFirstLastNameNotFoundException.class, () -> customerService.getCustomerByFirstAndLastName("John", "Doe"));
     
        }

        @Test
        public void testUpdateCustomerById_ExistingId() throws CustomerIdNotFoundException, CustomerCannotBeUpdatedException, MismatchDataTypeException {
     
            Customers existingCustomer = new Customers(1,"John", "Doe", "john@example.com", "1234567890", null);
     
            Customers updatedCustomer = new Customers(2,"John", "Smith", "john@example.com", "0987654321", null);

            when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));

            //assertEquals("Data updated", customerService.updateCustomersByCustomerId(1, updatedCustomer).getStatus());
     
        }


        @Test 
        public void testUpdateCustomerById_InvalidData() {
     
            Customers existingCustomer = new Customers(1, "John", "Doe", "john@example.com", "1234567890", null);
     
            Customers updatedCustomer = new Customers(2, "123", "Smith", "john@example.com", "0987654321", null);

            when(customerRepository.findById(1)).thenReturn(Optional.of(existingCustomer));

        //    assertThrows(CustomerCannotBeUpdatedException.class, () -> customerService.updateCustomersByCustomerId(1, updatedCustomer));
     
        }


        @Test
        public void testAddCustomers_InvalidData() {
     
            Customers customerToAdd = new Customers(1, "123", "Doe", "john@example.com", "1234567890", null);

        //    assertThrows(CustomerCannotBeAddedException.class, () -> customerService.addCustomers(customerToAdd));
     
        }


//        @Test
//        public void testGetAllPetsByCustomerId_NonExistingCustomerId() {
//     
//            when(transactionRepository.findByCustomer_CustomerId(1)).thenReturn(new ArrayList<>());
//
//            assertThrows(CustomerIdNotFoundException.class, () -> customerService.getAllPetsByCustomerId(1));
//     
//        }
     
}


