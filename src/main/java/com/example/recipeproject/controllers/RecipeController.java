package com.example.recipeproject.controllers;

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.exeptions.NotFoundException;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //    @GetMapping("/recipe/{id}/show")
//    public String showById(@PathVariable Long id, Model model) {
//
//        Recipe recipe = recipeService.findById(id);
//
//        model.addAttribute("recipe", recipe);
//
//        return "/recipe/show";
//    }
    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        Recipe recipe = recipeService.findById(Long.valueOf(id));

        model.addAttribute("recipe", recipe);

        return "/recipe/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        RecipeCommand command = recipeService.findCommandById(id);

        model.addAttribute("recipe", command);

        return "recipe/recipeForm";
    }

    @GetMapping("/recipe/new")
    public String recipeForm(Model model) {

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeForm";
    }

    @PostMapping("/recipe")
    public String saveOrUpdateRecipe(@Valid  @ModelAttribute("recipe") RecipeCommand command, BindingResult result) {

        if(result.hasErrors()){
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "recipe/recipeForm";
        }
        RecipeCommand newRecipe = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + newRecipe.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        log.debug("Deleting id: " + id);
        recipeService.deleteById(id);

        return "redirect:/index";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handle not found error");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("recipe/errors/error404");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
