package com.petshop.in.tests;

//public class GroomingServiceImplTest {
//
//}

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.model.GroomingServices;
import com.petshop.in.repository.GroomingServicesRepository;
import com.petshop.in.serviceimpl.GroomingServicesServiceImpl;



@SpringBootTest
public class GroomingServiceImplTest {

    @Mock
    private GroomingServicesRepository groomingServicesRepository;

    @InjectMocks
    private GroomingServicesServiceImpl groomingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllGroomingServices() {
        List<GroomingServices> mockList = new ArrayList<>();
        mockList.add(new GroomingServices());
        when(groomingServicesRepository.findAll()).thenReturn(mockList);

        assertDoesNotThrow(() -> {
            List<GroomingServices> result = groomingService.getAllGroomingServices();
            assertNotNull(result);
            assertEquals(mockList, result);
        });
    }

//    @Test
//    void testGetGroomingServicesByServiceId() {
//        int serviceId = 1;
//        GroomingServices mockService = new GroomingServices();
//        mockService.setId(serviceId);
//        when(groomingServicesRepository.findById(serviceId)).thenReturn(Optional.of(mockService));
//
//        assertDoesNotThrow(() -> {
//            GroomingServices result = groomingService.getGroomingServicesByServiceId(serviceId);
//            assertNotNull(result);
//            assertEquals(mockService, result);
//        });
//    }

    @Test
    void testGetAvailableGroomingServices() {
        List<GroomingServices> mockList = new ArrayList<>();
        mockList.add(new GroomingServices());
        when(groomingServicesRepository.findAllAvailable()).thenReturn(mockList);

        assertDoesNotThrow(() -> {
            List<GroomingServices> result = groomingService.getAllAvailableGroomingServices();
            assertNotNull(result);
            assertEquals(mockList, result);
        });
    }

    @Test
    void testGetUnavailableGroomingServices() {
        List<GroomingServices> mockList = new ArrayList<>();
        mockList.add(new GroomingServices());
        when(groomingServicesRepository.findAllUnavailable()).thenReturn(mockList);

        assertDoesNotThrow(() -> {
            List<GroomingServices> result = groomingService.getAllUnavailableGroomingServices();
            assertNotNull(result);
            assertEquals(mockList, result);
        });
    }

//    @Test
//    void testAddGroomingService() {
//        GroomingServices groomingServiceToAdd = new GroomingServices();
//        groomingServiceToAdd.setName("Bath");
//        groomingServiceToAdd.setDescription("Basic bath service");
//        groomingServiceToAdd.setAvailable(1);
//
//        when(groomingServicesRepository.save(groomingServiceToAdd)).thenReturn(groomingServiceToAdd);
//
//        assertDoesNotThrow(() -> {
//            SuccessResponse result = groomingService.addGroomingServices(groomingServiceToAdd);
//            assertNotNull(result);
//            assertEquals("Data added" + groomingServiceToAdd, result.getMessage());
//            assertEquals(LocalDate.now(), result.getTimeStamp());
//        });
//    }

//    @Test
//    void testUpdateGroomingService() {
//        int serviceId = 1;
//        GroomingServices existingService = new GroomingServices();
//        existingService.setId(serviceId);
//        existingService.setName("Bath");
//        existingService.setDescription("Basic bath service");
//        existingService.setAvailable(1);
//
//        GroomingServices updatedService = new GroomingServices();
//        updatedService.setName("Haircut");
//        updatedService.setDescription("Trimming service");
//        updatedService.setAvailable(0);
//
//        when(groomingServicesRepository.findById(serviceId)).thenReturn(Optional.of(existingService));
//        when(groomingServicesRepository.save(existingService)).thenReturn(existingService);
//
//        assertDoesNotThrow(() -> {
//            SuccessResponse result = groomingService.updateGroomingService(serviceId, updatedService);
//            assertNotNull(result);
//            assertEquals("Data Updated" + existingService, result.getMessage());
//            assertEquals(LocalDate.now(), result.getTimestamp());
//        });
    }


