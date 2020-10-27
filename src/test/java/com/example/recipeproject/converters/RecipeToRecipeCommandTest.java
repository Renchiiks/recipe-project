package com.example.recipeproject.converters;

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitsOfMeasureToUnitsOfMeasureCommand()),
                new CategoryToCategoryCommand());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        //given
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID_2);

        Set<Category> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        recipe.setUrl(URL);
        recipe.setIngredients(ingredients);
        recipe.setCategories(categories);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setServings(SERVINGS);
        recipe.setDirections(DIRECTIONS);
        recipe.setNotes(notes);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setCookTime(COOK_TIME);
        recipe.setSource(SOURCE);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getCategories().size());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());

    
    }
}