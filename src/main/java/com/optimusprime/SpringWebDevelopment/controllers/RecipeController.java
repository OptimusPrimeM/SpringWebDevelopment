package com.optimusprime.SpringWebDevelopment.controllers;

import com.optimusprime.SpringWebDevelopment.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/recipe/{id}"})
    public String getRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", this.recipeService.findById(new Long(id)));

        return "recipe/show";
    }
}
