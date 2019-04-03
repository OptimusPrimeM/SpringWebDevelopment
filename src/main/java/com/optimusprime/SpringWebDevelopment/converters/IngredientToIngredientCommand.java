package com.optimusprime.SpringWebDevelopment.converters;

import com.optimusprime.SpringWebDevelopment.commands.IngredientCommand;
import com.optimusprime.SpringWebDevelopment.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand measureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand measureToUnitOfMeasureCommand) {
        this.measureToUnitOfMeasureCommand = measureToUnitOfMeasureCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {

        if (ingredient == null)
            return null;

        final IngredientCommand ingredientCommand= new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setUnitOfMeasure(measureToUnitOfMeasureCommand.convert(ingredient.getUnitOfMeasure()));

        return  ingredientCommand;
    }
}
