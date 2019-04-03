package com.optimusprime.SpringWebDevelopment.commands;

import com.optimusprime.SpringWebDevelopment.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NotesCommand {

    private Long id;
    private String recipeNotes;

}
