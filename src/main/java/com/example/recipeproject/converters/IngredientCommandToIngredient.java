package com.example.recipeproject.converters;

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitsOfMeasure unitConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitsOfMeasure unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if (ingredientCommand == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();

        ingredient.setId(ingredientCommand.getId());

        if (ingredientCommand.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(ingredientCommand.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setMeasure(unitConverter.convert(ingredientCommand.getMeasure()));
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());

        return ingredient;
    }
}
