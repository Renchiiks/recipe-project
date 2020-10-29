package com.example.recipeproject.services;

import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.converters.UnitsOfMeasureToUnitsOfMeasureCommand;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitsOfMeasureServiceImpl implements UnitsOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitsOfMeasureToUnitsOfMeasureCommand toUnitsOfMeasureCommand;

    public UnitsOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                     UnitsOfMeasureToUnitsOfMeasureCommand toUnitsOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.toUnitsOfMeasureCommand = toUnitsOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> allMeasures() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(toUnitsOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
