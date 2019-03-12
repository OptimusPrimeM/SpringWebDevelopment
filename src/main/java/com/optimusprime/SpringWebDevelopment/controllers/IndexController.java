package com.optimusprime.SpringWebDevelopment.controllers;

import com.optimusprime.SpringWebDevelopment.domain.Category;
import com.optimusprime.SpringWebDevelopment.domain.UnitOfMeasure;
import com.optimusprime.SpringWebDevelopment.repositories.CategoryRepository;
import com.optimusprime.SpringWebDevelopment.repositories.UnitOfMeasureRepository;
import com.optimusprime.SpringWebDevelopment.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

   private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting index page.");

        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "index";
    }

}
