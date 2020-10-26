package com.example.recipeproject.bootstrap;

import com.example.recipeproject.model.*;
import com.example.recipeproject.repositories.CategoryRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class Data implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository measureRepository;
    private final RecipeRepository recipeRepository;

    public Data(CategoryRepository categoryRepository, UnitOfMeasureRepository measureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.measureRepository = measureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap data");
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> eachUOMOptional = measureRepository.findByUom("Unit");

        if (!eachUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUOMOptional = measureRepository.findByUom("Tablespoon");

        if (!tableSpoonUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUOMOptional = measureRepository.findByUom("Teaspoon");

        if (!teaSpoonUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUOMOptional = measureRepository.findByUom("Dash");

        if (!dashUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUOMOptional = measureRepository.findByUom("Pint");

        if (!pintUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUOMOptional = measureRepository.findByUom("Cup");

        if (!cupsUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        Optional<UnitOfMeasure> cloveUOMOptional = measureRepository.findByUom("Clove");

        if (!cloveUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        Optional<UnitOfMeasure> poundUOMOptional = measureRepository.findByUom("Pound");

        if (!poundUOMOptional.isPresent()) {
            throw new RuntimeException("Exception UOM Not Found");
        }

        UnitOfMeasure unit = eachUOMOptional.get();
        UnitOfMeasure tableSpoonUOM = tableSpoonUOMOptional.get();
        UnitOfMeasure teaSpoonUOM = teaSpoonUOMOptional.get();
        UnitOfMeasure dashUOM = dashUOMOptional.get();
        UnitOfMeasure pintUOM = pintUOMOptional.get();
        UnitOfMeasure cupUOM = cupsUOMOptional.get();
        UnitOfMeasure cloveUOM = cloveUOMOptional.get();
        UnitOfMeasure poundUOM = poundUOMOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Exception Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Exception Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Guacamole");
        guacamoleRecipe.getCategories().add(mexicanCategory);
        guacamoleRecipe.setCookTime(10);
        guacamoleRecipe.setPrepTime(15);
        guacamoleRecipe.setServings(2);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        Notes guacamoleNote = new Notes();
        guacamoleNote.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        guacamoleRecipe.setNotes(guacamoleNote);

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocado", new BigDecimal(2), unit));
        guacamoleRecipe.addIngredient(new Ingredient("salt", new BigDecimal("0.25"), teaSpoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice", new BigDecimal(1), tableSpoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("onion", new BigDecimal(2), tableSpoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("serrano chiles", new BigDecimal(1), unit));
        guacamoleRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("black papper", new BigDecimal(1), dashUOM));
        guacamoleRecipe.addIngredient(new Ingredient("tomato", new BigDecimal("0.5"), unit));
        guacamoleRecipe.addIngredient(new Ingredient("reddish", new BigDecimal(1), unit));
        guacamoleRecipe.addIngredient(new Ingredient("tortilla", new BigDecimal(2), unit));
        guacamoleRecipe.setSource("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        recipes.add(guacamoleRecipe);

        Recipe chickenTacos = new Recipe();
        chickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        chickenTacos.setPrepTime(20);
        chickenTacos.setServings(5);
        chickenTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        chickenTacos.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tableSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("salt", new BigDecimal("0.5"), teaSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("garlic, finely chopped", new BigDecimal(1), cloveUOM));
        chickenTacos.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tableSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tableSpoonUOM));
        chickenTacos.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal("1.25"), poundUOM));
        chickenTacos.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), unit));
        chickenTacos.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUOM));
        chickenTacos.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2), unit));
        chickenTacos.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), unit));
        chickenTacos.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal("0.5"), pintUOM));
        chickenTacos.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal("0.25"), unit));
        chickenTacos.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(1), unit));
        chickenTacos.addIngredient(new Ingredient("our cream thinned", new BigDecimal("0.5"), cupUOM));
        chickenTacos.addIngredient(new Ingredient("milk", new BigDecimal("0.25"), cupUOM));
        chickenTacos.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), unit));

        Notes chickenTacosNote = new Notes();
        chickenTacosNote.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");

        chickenTacos.setNotes(chickenTacosNote);
        chickenTacos.setDifficulty(Difficulty.MODERATE);
        chickenTacos.setCookTime(15);
        chickenTacos.getCategories().add(mexicanCategory);
        chickenTacos.getCategories().add(americanCategory);
        chickenTacos.setSource("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        recipes.add(chickenTacos);

        return recipes;
    }


}
