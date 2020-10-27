package com.example.recipeproject.converters;

import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitsOfMeasureToUnitsOfMeasureCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String UOM = "Unit";

    UnitsOfMeasureToUnitsOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitsOfMeasureToUnitsOfMeasureCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasure measure = new UnitOfMeasure();
        measure.setId(ID_VALUE);
        measure.setUom(UOM);

        //when
        UnitOfMeasureCommand command  = converter.convert(measure);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(UOM, command.getUom());
    }
}