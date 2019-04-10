package com.optimusprime.SpringWebDevelopment.services;

import com.optimusprime.SpringWebDevelopment.commands.RecipeCommand;
import com.optimusprime.SpringWebDevelopment.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();
    Recipe findById(long l);
    RecipeCommand savedRecipe(RecipeCommand recipeCommand);
}
