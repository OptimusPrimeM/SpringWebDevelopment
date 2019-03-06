package com.optimusprime.SpringWebDevelopment.services;

import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import com.optimusprime.SpringWebDevelopment.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAllRecipes() {

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
//        return (Set<Recipe>) recipeRepository.findAll();
    }
}
