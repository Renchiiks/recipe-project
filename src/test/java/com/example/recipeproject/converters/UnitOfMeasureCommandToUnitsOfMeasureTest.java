package com.example.recipeproject.converters;

import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitsOfMeasureTest {

    public static final Long ID_VALUE = 1L;
    public static final String UOM = "Unit";

    UnitOfMeasureCommandToUnitsOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitsOfMeasure();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID_VALUE);
        command.setUom(UOM);

        //when
        UnitOfMeasure measure = converter.convert(command);

        //then
        assertNotNull(measure);
        assertEquals(ID_VALUE, measure.getId());
        assertEquals(UOM, measure.getUom());
    }
}