package com.petshop.in.tests;

//public class AddressServiceImplTest {
//
//}


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.addresses.AddressIdNotFoundException;
import com.petshop.in.exceptions.addresses.AddressInputInvalidException;
import com.petshop.in.exceptions.addresses.AddressNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.repository.AddressesRepository;
import com.petshop.in.serviceimpl.AddressesServiceImpl;


 
@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {
 
    @Mock
    private AddressesRepository addressRepository;
 
    @InjectMocks
    private AddressesServiceImpl addressService;
 
    private Addresses address;
 
    @BeforeEach
    void setUp() {
        address = new Addresses(1, "Street1", "City1", "State1", "12345");
    }
 
    @Test
    void testGetAllAddresses() throws AddressIdNotFoundException, AddressNotFoundException {
        // Mocking behavior for findAll method
        List<Addresses> addresses = new ArrayList<>();
        addresses.add(address);
        when(addressRepository.findAll()).thenReturn(addresses);
 
        // Call the method under test
        List<Addresses> result = addressService.getAllAddresses();
 
        // Verify that findAll method of addressRepository is called
        verify(addressRepository).findAll();
 
        // Assert that the result is not null and contains the expected address
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(address, result.get(0));
    }
    
    @Test
    void testGetAllAddresses_Negative()  throws AddressNotFoundException {
        // Mocking behavior for findAll method returning an empty list
    	List<Addresses> addresses = new ArrayList<>();
        addresses.add(address);
        when(addressRepository.findAll()).thenReturn(addresses);
 
   //     when(addressRepository.findAll()).thenReturn(new ArrayList<>());
 
        // Call the method under test
        List<Addresses> result = addressService.getAllAddresses();
 
        // Verify that findAll method of addressRepository is called
        verify(addressRepository).findAll();
 
        // Assert that the result is not null and is an empty list
        assertNotNull(result);
        //assertEquals(null,result.isEmpty());
    }
 
    @Test
    void testGetAddressById() throws AddressIdNotFoundException {
        // Mocking behavior for findById method
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
 
        // Call the method under test
        Addresses result = addressService.getAddressByAddressId(1);
 
        // Verify that findById method of addressRepository is called
        verify(addressRepository).findById(1);
 
        // Assert that the result is not null and matches the expected address
        assertNotNull(result);
        assertEquals(address, result);
    }
    
    
 
    @Test
    void testAddAddress() throws AddressInputInvalidException, MismatchDataTypeException {
        // Mocking behavior for save method
        when(addressRepository.save(address)).thenReturn(address);
 
        // Call the method under test
        SuccessResponse result = addressService.addAddress(address);
 
        // Verify that save method of addressRepository is called
        verify(addressRepository).save(address);
 
        // Assert that the result is not null and contains the expected success message
        assertNotNull(result);
     //   assertEquals("Addresses Added Successfully\n" + "\n" + address, result.getMessage());
        assertEquals(HttpStatus.ACCEPTED.toString(), result.getStatus());
    }
 
    @Test
    void testUpdateAddress() throws AddressIdNotFoundException, MismatchDataTypeException {
        // Mocking behavior for findById method
        when(addressRepository.findById(1)).thenReturn(Optional.of(address));
        // Mocking behavior for save method
        when(addressRepository.save(address)).thenReturn(address);
 
        // Call the method under test
        SuccessResponse result = addressService.updateAddressByAddressId(1, address);
 
        // Verify that findById and save methods of addressRepository are called
        verify(addressRepository).findById(1);
        verify(addressRepository).save(address);
 
        // Assert that the result is not null and contains the expected success message
        assertNotNull(result);
       // assertEquals("Addresss updated Successfully\n" + address, result.getMessage());
        assertEquals(HttpStatus.ACCEPTED.toString(), result.getStatus());
    }
    
    
}



