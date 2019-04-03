package com.optimusprime.SpringWebDevelopment.commands;

import com.optimusprime.SpringWebDevelopment.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String direction;
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<IngredientCommand> ingredient = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
}
