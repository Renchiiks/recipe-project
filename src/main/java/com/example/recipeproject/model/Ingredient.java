package com.example.recipeproject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
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

}
