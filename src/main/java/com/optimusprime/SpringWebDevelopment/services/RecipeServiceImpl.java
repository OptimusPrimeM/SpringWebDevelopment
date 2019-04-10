package com.optimusprime.SpringWebDevelopment.services;

import com.optimusprime.SpringWebDevelopment.commands.RecipeCommand;
import com.optimusprime.SpringWebDevelopment.converters.RecipeCommandToRecipe;
import com.optimusprime.SpringWebDevelopment.converters.RecipeToRecipeCommand;
import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import com.optimusprime.SpringWebDevelopment.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand,
                             RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
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

    @Override
    @Transactional
    public RecipeCommand savedRecipe(RecipeCommand recipeCommand) {

        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved recipe ID = %s", savedRecipe.getId() );
        return recipeToRecipeCommand.convert(savedRecipe);
    }


}
