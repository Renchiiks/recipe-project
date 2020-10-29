package com.example.recipeproject.services;

import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.converters.UnitsOfMeasureToUnitsOfMeasureCommand;
import com.example.recipeproject.model.UnitOfMeasure;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class UnitsOfMeasureServiceImplTest {

    UnitsOfMeasureToUnitsOfMeasureCommand unitsOfMeasureCommand = new UnitsOfMeasureToUnitsOfMeasureCommand();
    UnitsOfMeasureService service;

    @Mock
    UnitOfMeasureRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UnitsOfMeasureServiceImpl(repository, unitsOfMeasureCommand);

    }

    @Test
    void allMeasures() {
        //given
        Set<UnitOfMeasure> measures = new HashSet<>();

        UnitOfMeasure measure1 = new UnitOfMeasure();
        measure1.setId(1L);

        UnitOfMeasure measure2 = new UnitOfMeasure();
        measure1.setId(2L);

        measures.add(measure1);
        measures.add(measure2);

        Mockito.when(repository.findAll()).thenReturn(measures);

        //when
        Set<UnitOfMeasureCommand> measureCommands = service.allMeasures();

        //then
        assertEquals(2, measureCommands.size());
        verify(repository).findAll();
    }
}