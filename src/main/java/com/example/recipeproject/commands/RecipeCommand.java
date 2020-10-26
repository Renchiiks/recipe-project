package com.example.recipeproject.commands;

import com.example.recipeproject.model.Category;
import com.example.recipeproject.model.Difficulty;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.model.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;
    private Notes notes;
    private Set<Ingredient> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private Set<Category> categories = new HashSet<>();
}
