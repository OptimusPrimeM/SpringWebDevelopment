package com.optimusprime.SpringWebDevelopment.converters;

import com.optimusprime.SpringWebDevelopment.commands.CategoryCommand;
import com.optimusprime.SpringWebDevelopment.commands.IngredientCommand;
import com.optimusprime.SpringWebDevelopment.commands.NotesCommand;
import com.optimusprime.SpringWebDevelopment.commands.RecipeCommand;
import com.optimusprime.SpringWebDevelopment.domain.Notes;
import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import com.optimusprime.SpringWebDevelopment.enums.Difficulty;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class RecipeCommandToRecipeTest {

    RecipeCommandToRecipe conveter;

    public static final Long RECIPE_ID = 1L;
    public static final Long NOTE_ID = 2L;
    public static final Long CATEGORY_ID1 = 3L;
    public static final Long CATEGORY_ID2 = 4L;
    public static final Long INGREDIENT_ID1 = 3L;
    public static final Long INGREDIENT_ID2 = 4L;
    public static final String DESCRIPTION = "My Recipe";
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;




    @Before
    public void setUp() throws Exception {
       conveter = new RecipeCommandToRecipe(
               new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
               new CategoryCommandToCategory(),
               new NotesCommandToNotes()
       );

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    @Ignore
    public void testWithNullDependencies() throws Exception {

        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirection(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setNotes(new NotesCommand());
        recipeCommand.getIngredient().add(null);
        recipeCommand.getCategories().add(null);

        //when
       Recipe recipe =  conveter.convert(recipeCommand);

       //then
        assertNotNull(recipe);
        assertNull(recipe.getCategories());
        assertNull(recipe.getIngredient());
        assertNotNull(recipe.getNotes());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirection());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(0, recipe.getCategories().size());
        assertEquals(0, recipe.getIngredient().size());


    }

    @Test
    public void testWithDependencies() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirection(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTE_ID);
        recipeCommand.setNotes(notes);

        CategoryCommand categoryCommand_1 = new CategoryCommand();
        categoryCommand_1.setId(CATEGORY_ID1);
        recipeCommand.getCategories().add(categoryCommand_1);

        CategoryCommand categoryCommand_2 = new CategoryCommand();
        categoryCommand_2.setId(CATEGORY_ID2);
        recipeCommand.getCategories().add(categoryCommand_2);

        IngredientCommand ingredientCommand_1 = new IngredientCommand();
        ingredientCommand_1.setId(INGREDIENT_ID1);
        recipeCommand.getIngredient().add(ingredientCommand_1);

        IngredientCommand ingredientCommand_2 = new IngredientCommand();
        ingredientCommand_2.setId(INGREDIENT_ID2);
        recipeCommand.getIngredient().add(ingredientCommand_2);


        //when
        Recipe recipe =  conveter.convert(recipeCommand);

        //then
        assertNotNull(recipe);
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getIngredient());
        assertNotNull(recipe.getNotes());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirection());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredient().size());
    }
}