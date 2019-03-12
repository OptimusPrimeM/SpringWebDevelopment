package com.optimusprime.SpringWebDevelopment.bootstrap;

import com.optimusprime.SpringWebDevelopment.domain.*;
import com.optimusprime.SpringWebDevelopment.enums.Difficulty;
import com.optimusprime.SpringWebDevelopment.repositories.CategoryRepository;
import com.optimusprime.SpringWebDevelopment.repositories.RecipeRepository;
import com.optimusprime.SpringWebDevelopment.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }


    List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();

      /*Getting UOM*/

        Optional<UnitOfMeasure> eachUOMOptional = unitOfMeasureRepository.findByDescription("Each");

        if (!eachUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM is not found");
        }

        Optional<UnitOfMeasure> teaSpoonUOMOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaSpoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM is not found");
        }

        Optional<UnitOfMeasure> tableSpoonUOMOptional = unitOfMeasureRepository.findByDescription("TableSpoon");

        if (!tableSpoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM is not found");
        }

        Optional<UnitOfMeasure> dashUOMOptional = unitOfMeasureRepository.findByDescription("Dash");

        if (!dashUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM is not found");
        }

           /*        Getting Each of UOM from optional    */

        UnitOfMeasure eachUom = eachUOMOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUOMOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUOMOptional.get();
        UnitOfMeasure dashUom = dashUOMOptional.get();


      /*Getting Categories*/

        Optional<Category> maxicanCatOptional = categoryRepository.findByDescription("Mexican");

            /*        Getting Each of UOM from optional    */

        Category maxicanCategory = maxicanCatOptional.get();

          /* Creating Perfect Guacamole Recipe*/

        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirection("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt" +
                " knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky." +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some" +
                " balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of" +
                " one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to " +
                "your taste" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. " +
                "(The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");


        /*Creating Note*/
        Notes guacamoleNote = new Notes();
        guacamoleNote.setRecipeNotes("All you really need to make guacamole is ripe avocados and salt. After that, a little lime or lemon juice—a" +
                " splash of acidity—will help to balance the richness of the avocado. Then if you want, add chopped cilantro, chiles, onion, and/or tomato.\n" +
                "Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon." +
                " You can get creative with homemade guacamole");


        guacamole.setNotes(guacamoleNote);

        /*Adding Ingredients*/
        guacamole.addIngredient(new Ingredient("Ripe avocados", new BigDecimal(2),eachUom));
        guacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5),teaSpoonUom));
        guacamole.addIngredient(new Ingredient("Fresh lime juice or lemon juice", new BigDecimal(1),tableSpoonUom));
        guacamole.addIngredient(new Ingredient("Minced red onion or thinly sliced green onions", new BigDecimal(2),tableSpoonUom));
        guacamole.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(1),eachUom));
        guacamole.addIngredient(new Ingredient("Cilantro (leaves and tender stems), finely chopped", new BigDecimal(2),tableSpoonUom));
        guacamole.addIngredient(new Ingredient("Freshly grated black pepper", new BigDecimal(1),dashUom));
        guacamole.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, choppeds", new BigDecimal(0.5),eachUom));


        /*Adding Category*/
        guacamole.getCategories().add(maxicanCategory);

        /*Adding Recipe to Note*/
        guacamoleNote.setRecipe(guacamole);


        /*Add to recipe list*/
        recipes.add(guacamole);

        return recipes;

    }


}
