package com.optimusprime.SpringWebDevelopment.controllers;

import com.optimusprime.SpringWebDevelopment.domain.Category;
import com.optimusprime.SpringWebDevelopment.domain.UnitOfMeasure;
import com.optimusprime.SpringWebDevelopment.repositories.CategoryRepository;
import com.optimusprime.SpringWebDevelopment.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("Italian");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Ounce");

        System.out.println("Category ID = " + categoryOptional.get().getId());
        System.out.println("Unit Of measure ID = " + unitOfMeasureOptional.get().getId());
        return "index";
    }
}
