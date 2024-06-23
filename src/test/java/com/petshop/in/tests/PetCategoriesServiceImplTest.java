package com.petshop.in.tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.petcategories.CategoryAddException;
import com.petshop.in.exceptions.petcategories.CategoryIdNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNameNotFoundException;
import com.petshop.in.exceptions.petcategories.CategoryNotFoundException;
import com.petshop.in.model.PetCategories;
import com.petshop.in.repository.PetCategoriesRepository;
import com.petshop.in.serviceimpl.PetCategoriesServiceImpl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class PetCategoriesServiceImplTest {

    @Mock
    private PetCategoriesRepository petCategoriesRepositoryMock;

    @InjectMocks
    private PetCategoriesServiceImpl petCategoriesService;

    private PetCategories petCategory;

    @BeforeEach
    void setUp() {
        petCategory = new PetCategories();
        //petCategory.setId(1);
        petCategory.setName("Test Category");
    }

    @Test
    void testRetrieveAllCategories() throws CategoryNotFoundException {
        List<PetCategories> categoriesList = new ArrayList<>();
        categoriesList.add(petCategory);
        
        when(petCategoriesRepositoryMock.findAll()).thenReturn(categoriesList);
        
        List<PetCategories> result = petCategoriesService.getAllPetCategories();
        
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Test Category", result.get(0).getName());
    }

    @Test
    void testRetrievePetCategoriesId_ExistingId() throws CategoryIdNotFoundException {
        when(petCategoriesRepositoryMock.findById(1)).thenReturn(Optional.of(petCategory));
        
        PetCategories result = petCategoriesService.getPetCategoriesByCategoryId(1);
        
        assertNotNull(result);
        assertEquals("Test Category", result.getName());
    }

    @Test
    void testRetrievePetCategoriesId_NonExistingId() {
        when(petCategoriesRepositoryMock.findById(2)).thenReturn(Optional.empty());
        
        assertThrows(CategoryIdNotFoundException.class, () -> {
            petCategoriesService.getPetCategoriesByCategoryId(2);
        });
    }
    
    @Test
    public void testRetrievePetCategories_NotFound() throws CategoryNameNotFoundException {
        when(petCategoriesRepositoryMock.findByName("Category1")).thenReturn(new ArrayList<>());

        petCategoriesService.getPetCategoryByCategoryName("Category1");
    }

//    @Test
//    public void testAddCategory() throws MismatchDataTypeException, CategoryAddException {
//        PetCategories category = new PetCategories(1, "Category1");
//        when(petCategoriesRepositoryMock.save(category)).thenReturn(category);
//
//        SuccessResponse result = petCategoriesService.addCategory(category);
//
//        assertNotNull(result);
//        assertEquals("Category1", result.getMessage());
//    }



//    @Test
//    public void testUpdateCategoryName() throws MismatchDataTypeException, CategoryIdNotFoundException {
//        PetCategories category = new PetCategories(1, "Category1");
//        when(petCategoriesRepositoryMock.findById(1)).thenReturn(Optional.of(category));
//        when(petCategoriesRepositoryMock.save(category)).thenReturn(category);
//
//        SuccessResponse result = petCategoriesService.updateCategoryByCategoryId(1, category);
//
//        assertNotNull(result);
//        assertEquals("Data Updated: PetCategories [categoryId=1, name=UpdatedCategory]", result.getMessage());
//    }

   

}
