package com.optimusprime.SpringWebDevelopment.converters;

import com.optimusprime.SpringWebDevelopment.commands.RecipeCommand;
import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private CategoryCommandToCategory categoryCommandToCategory;
    private NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 NotesCommandToNotes notesCommandToNotes) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {

        if (recipeCommand == null)
            return null;

        final Recipe recipe = new Recipe();

        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirection(recipeCommand.getDirection());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));

        if (recipeCommand.getIngredient() != null && recipeCommand.getIngredient().size() > 0) {

            recipeCommand.getIngredient()
                    .forEach(ingredientCommand -> recipe.getIngredient().add(ingredientCommandToIngredient.convert(ingredientCommand)));

        }

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {

            recipeCommand.getCategories()
                    .forEach(categoryCommand -> recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
        }

        return recipe;


    }
}
