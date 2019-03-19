package com.optimusprime.SpringWebDevelopment.services;

import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import com.optimusprime.SpringWebDevelopment.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RecipeServiceImplTest {

    RecipeServiceImpl recipeServiceImpl;

    @Mock
    RecipeRepository recipeRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getAllRecipes() throws Exception {

        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeServiceImpl.getAllRecipes()).thenReturn(recipeData);


        Set<Recipe> recipeSet = recipeServiceImpl.getAllRecipes();

       /*This will pass
        assertEquals(recipeSet.size(), 0); */

        /*This will fail*/
        assertEquals(recipeSet.size(), 1);

        verify(recipeRepository, times(1)).findAll();
    }

}