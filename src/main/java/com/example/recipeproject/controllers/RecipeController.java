package com.example.recipeproject.controllers;

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model) {

        Recipe recipe = recipeService.findById(id);

        model.addAttribute("recipe", recipe);

        return "/recipe/show";
    }

    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        RecipeCommand command = recipeService.findCommandById(id);
        model.addAttribute("recipe", command);

        return "recipe/recipeForm";
    }

    @RequestMapping("/recipe/new")
    public String recipeForm(Model model) {

        model.addAttribute("recipe", new Recipe());

        return "recipe/recipeForm";
    }

    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand command) {
        RecipeCommand newRecipe = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + newRecipe.getId() + "/show";
    }
}
