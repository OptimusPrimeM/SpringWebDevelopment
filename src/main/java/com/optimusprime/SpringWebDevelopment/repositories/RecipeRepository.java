package com.optimusprime.SpringWebDevelopment.repositories;

import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
