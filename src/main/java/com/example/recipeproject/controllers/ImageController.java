package com.example.recipeproject.controllers;

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.services.ImageService;
import com.example.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("recipe/{recipeId}/image")
    public String imageForm(@PathVariable Long recipeId, Model model) {
        RecipeCommand recipe = recipeService.findCommandById(recipeId);
        model.addAttribute("recipe", recipe);

        return "recipe/imageForm";
    }

    @PostMapping("recipe/{recipeId}/image")
    public String uploadImage(@PathVariable Long recipeId, @RequestParam("imageFile") MultipartFile multipartFile) {
        imageService.saveImageFile(recipeId, multipartFile);

        return "redirect:/recipe/" + recipeId + "/show";
    }
}
