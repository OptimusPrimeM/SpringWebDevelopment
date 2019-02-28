package com.optimusprime.SpringWebDevelopment.repositories;

import com.optimusprime.SpringWebDevelopment.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
