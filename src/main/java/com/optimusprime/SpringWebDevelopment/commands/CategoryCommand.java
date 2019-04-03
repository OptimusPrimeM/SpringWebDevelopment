package com.optimusprime.SpringWebDevelopment.commands;

import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;
    private String description;

}
