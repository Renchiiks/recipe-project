package com.example.recipeproject.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure measure;


    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure measure) {
        this.description = description;
        this.amount = amount;
        this.measure = measure;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure measure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.measure = measure;
        this.recipe = recipe;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getMeasure() {
        return measure;
    }

    public void setMeasure(UnitOfMeasure measure) {
        this.measure = measure;
    }
}
