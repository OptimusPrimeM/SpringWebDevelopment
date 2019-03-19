package com.optimusprime.SpringWebDevelopment.repositories;

import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
