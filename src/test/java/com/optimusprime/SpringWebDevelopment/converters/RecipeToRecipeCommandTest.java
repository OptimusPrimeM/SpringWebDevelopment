package com.optimusprime.SpringWebDevelopment.converters;

import com.optimusprime.SpringWebDevelopment.commands.RecipeCommand;
import com.optimusprime.SpringWebDevelopment.domain.Category;
import com.optimusprime.SpringWebDevelopment.domain.Ingredient;
import com.optimusprime.SpringWebDevelopment.domain.Notes;
import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import com.optimusprime.SpringWebDevelopment.enums.Difficulty;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private RecipeToRecipeCommand converter;
    private static final Long RECIPE_ID = 1L;
    private static final Long NOTE_ID = 2L;
    private static final Long CATEGORY_ID1 = 3L;
    private static final Long CATEGORY_ID2 = 4L;
    private static final Long INGREDIENT_ID1 = 3L;
    private static final Long INGREDIENT_ID2 = 4L;
    private static final String DESCRIPTION = "My Recipe";
    private static final Integer PREP_TIME = Integer.valueOf("7");
    private static final Integer COOK_TIME = Integer.valueOf("5");
    private static final Integer SERVINGS = Integer.valueOf("3");
    private static final String SOURCE = "Source";
    private static final String URL = "Some URL";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;


    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(),
                new NotesToNotesCommand()
        );
    }

    @Test
    public void testNullParameters() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    @Ignore
    public void testWithNullDependencies() throws Exception {

        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirection(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setNotes(new Notes());
        recipe.getIngredient().add(null);
        recipe.getCategories().add(null);

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertNotNull(recipeCommand);
        assertNull(recipeCommand.getCategories());
        assertNull(recipeCommand.getIngredient());
        assertNotNull(recipeCommand.getNotes());
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTIONS, recipeCommand.getDirection());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(0, recipe.getCategories().size());
        assertEquals(0, recipeCommand.getIngredient().size());

    }

    @Test
    public void testWithDependencies() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirection(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTE_ID);
        recipe.setNotes(notes);

        Category category_1 = new Category();
        category_1.setId(CATEGORY_ID1);
        recipe.getCategories().add(category_1);

        Category category_2 = new Category();
        category_2.setId(CATEGORY_ID2);
        recipe.getCategories().add(category_2);

        Ingredient ingredient_1 = new Ingredient();
        ingredient_1.setId(INGREDIENT_ID1);
        recipe.getIngredient().add(ingredient_1);

        Ingredient ingredient_2 = new Ingredient();
        ingredient_2.setId(INGREDIENT_ID2);
        recipe.getIngredient().add(ingredient_2);


        //when
        RecipeCommand recipeCommand =  converter.convert(recipe);

        //then
        assertNotNull(recipeCommand);
        assertNotNull(recipeCommand.getCategories());
        assertNotNull(recipeCommand.getIngredient());
        assertNotNull(recipeCommand.getNotes());
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(DIRECTIONS, recipeCommand.getDirection());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredient().size());
    }
}