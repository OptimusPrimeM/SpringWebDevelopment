package com.optimusprime.SpringWebDevelopment.services;

import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import com.optimusprime.SpringWebDevelopment.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAllRecipes() {

        log.debug("I'm in the service.");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
//        return (Set<Recipe>) recipeRepository.findAll();
    }

    @Override
    public Recipe findById(long l) {

        Optional<Recipe> recipeOptional = this.recipeRepository.findById(l);

        if (!recipeOptional.isPresent())
            throw new RuntimeException("Recipe not found!");

        return recipeOptional.get();

    }
}
