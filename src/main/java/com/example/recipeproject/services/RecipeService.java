package com.example.recipeproject.services;

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long id);
}
