package com.example.recipeproject.service;

import com.example.recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}