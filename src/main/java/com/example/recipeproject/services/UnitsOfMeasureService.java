package com.example.recipeproject.services;

import com.example.recipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitsOfMeasureService {
    Set<UnitOfMeasureCommand> allMeasures();
}
