package com.example.recipeproject.converters;

import com.example.recipeproject.commands.CategoryCommand;
import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.commands.NotesCommand;
import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Difficulty;
import com.example.recipeproject.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {
    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "Description";
    public static final Integer PREP_TIME = 15;
    public static final Integer COOK_TIME = 30;
    public static final Integer SERVINGS = 2;
    public static final String SOURCE = "Source";
    public static final String URL = "url";
    public static final String DIRECTIONS = "Directions";
    public static final Long NOTES_ID = 3L;
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long CAT_ID_1 = 4L;
    public static final Long CAT_ID_2 = 5L;
    public static final Long INGRED_ID_1 = 6L;
    public static final Long INGRED_ID_2 = 7L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitsOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        //given
        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        Set<IngredientCommand> ingredients = new HashSet<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID_2);

        Set<CategoryCommand> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        RecipeCommand command = new RecipeCommand();
        command.setId(ID_VALUE);
        command.setUrl(URL);
        command.setIngredients(ingredients);
        command.setCategories(categories);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setServings(SERVINGS);
        command.setDirections(DIRECTIONS);
        command.setNotes(notes);
        command.setDifficulty(DIFFICULTY);
        command.setCookTime(COOK_TIME);
        command.setSource(SOURCE);

        //when
        Recipe recipe = converter.convert(command);

        //then
        assertNotNull(recipe);
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());

    }
}