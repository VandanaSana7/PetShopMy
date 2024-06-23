package com.petshop.in.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import com.petshop.in.exceptions.pets.NoPetCategoryFoundException;
import com.petshop.in.exceptions.pets.PetIdNotFoundException;
import com.petshop.in.exceptions.pets.PetsNotFoundException;
import com.petshop.in.model.Pets;
import com.petshop.in.repository.PetCategoriesRepository;
import com.petshop.in.repository.PetsRepository;
import com.petshop.in.serviceimpl.PetServiceImpl;


@SpringBootTest
public class PetServiceImplTest {

    private static final CrudRepository<Pets, Integer> petRepository = null;

	@Mock
    private PetsRepository petsRepository;

    @Mock
    private PetCategoriesRepository petCategoriesRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllPets() {
        List<Pets> petsList = new ArrayList<>();
        petsList.add(new Pets());
        when(petsRepository.findAll()).thenReturn(petsList);

        assertDoesNotThrow(() -> petService.getAllPets());
    }

    @Test
    public void testRetrieveAllPets_NoPetsFoundException() {
        when(petsRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(PetsNotFoundException.class, () -> petService.getAllPets());
    }

    @Test
    public void testRetrieveById() {
        Pets pet = new Pets();
        //pet.setId(1);
        Optional<Pets> optionalPet = Optional.of(pet);
        when(petsRepository.findById(1)).thenReturn(optionalPet);

        assertDoesNotThrow(() -> petService.getPetsById(1));
    }

    @Test
    public void testRetrieveById_PetIdNotFoundException() {
        when(petsRepository.findById(1)).thenThrow(NoSuchElementException.class);

        assertThrows(PetIdNotFoundException.class, () -> petService.getPetsById(1));
    }

    @Test
    public void testGetPetsByCategory() {
        List<Pets> petsList = new ArrayList<>();
        petsList.add(new Pets());
        when(petsRepository.findByPetCategoryName("Category")).thenReturn(petsList);

        assertDoesNotThrow(() -> petService.getPetsByCategory("Category"));
    }

    @Test
    public void testGetPetsByCategory_PetCategoryNotFoundException() {
        when(petsRepository.findByPetCategoryName("Category")).thenReturn(new ArrayList<>());

        assertThrows(NoPetCategoryFoundException.class, () -> petService.getPetsByCategory("Category"));
    }
   
   
}


