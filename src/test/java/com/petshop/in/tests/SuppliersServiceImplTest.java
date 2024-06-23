package com.petshop.in.tests;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.petshop.in.exceptions.suppliers.SupplierCityNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierStateNotFoundException;
import com.petshop.in.exceptions.suppliers.SuppliersNotFoundException;
import com.petshop.in.model.Suppliers;
import com.petshop.in.repository.SuppliersRepository;
import com.petshop.in.serviceimpl.SuppliersServiceImpl;



@SpringBootTest
public class SuppliersServiceImplTest {

    @Mock
    private SuppliersRepository suppliersRepository;

    @InjectMocks
    private SuppliersServiceImpl suppliersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSuppliers() {
        List<Suppliers> suppliersList = new ArrayList<>();
        suppliersList.add(new Suppliers());
        when(suppliersRepository.findAll()).thenReturn(suppliersList);

        assertDoesNotThrow(() -> suppliersService.getAllSuppliers());
    }

    @Test
    public void testGetAllSuppliers_SupplierNotFoundException() {
        when(suppliersRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(SuppliersNotFoundException.class, () -> suppliersService.getAllSuppliers());
    }

    @Test
    public void testGetSupplierById() {
        Suppliers supplier = new Suppliers();
        //supplier.setId(1);
        Optional<Suppliers> optionalSupplier = Optional.of(supplier);
        when(suppliersRepository.findById(1)).thenReturn(optionalSupplier);

        assertDoesNotThrow(() -> suppliersService.getSuppliersBySupplierId(1));
    }

    @Test
    public void testGetSupplierById_SupplierIdNotFoundException() {
        when(suppliersRepository.findById(1)).thenThrow(NoSuchElementException.class);

        assertThrows(SupplierIdNotFoundException.class, () -> suppliersService.getSuppliersBySupplierId(1));
    }

    @Test
    public void testGetSuppliersByName() {
        List<Suppliers> suppliersList = new ArrayList<>();
        suppliersList.add(new Suppliers());
        when(suppliersRepository.findByName("SupplierName")).thenReturn(suppliersList);

        assertDoesNotThrow(() -> suppliersService.getSuppliersByName("SupplierName"));
    }

    @Test
    public void testGetSuppliersByName_SupplierNameNotFoundException() {
        when(suppliersRepository.findByName("SupplierName")).thenReturn(new ArrayList<>());

        assertThrows(SupplierNameNotFoundException.class, () -> suppliersService.getSuppliersByName("SupplierName"));
    }

    @Test
    public void testGetSuppliersByCity() {
        List<Suppliers> suppliersList = new ArrayList<>();
        suppliersList.add(new Suppliers());
        when(suppliersRepository.findByCityName("SupplierCity")).thenReturn(suppliersList);

        assertDoesNotThrow(() -> suppliersService.getSuppliersByCityName("SupplierCity"));
    }

    @Test
    public void testGetSuppliersByCity_SupplierNameNotFoundException() {
        when(suppliersRepository.findByCityName("SupplierCity")).thenReturn(new ArrayList<>());

        assertThrows(SupplierCityNotFoundException.class, () -> suppliersService.getSuppliersByCityName("SupplierCity"));
    }

    @Test
    public void testGetSuppliersByState() {
        List<Suppliers> suppliersList = new ArrayList<>();
        suppliersList.add(new Suppliers());
        when(suppliersRepository.findByStateName("SupplierState")).thenReturn(suppliersList);

        assertDoesNotThrow(() -> suppliersService.getSuppliersByStateName("SupplierState"));
    }

    @Test
    public void testGetSuppliersByState_SupplierCityNotFoundException() {
        when(suppliersRepository.findByStateName("SupplierState")).thenReturn(new ArrayList<>());

        assertThrows(SupplierStateNotFoundException.class, () -> suppliersService.getSuppliersByStateName("SupplierState"));
    }

    
}




