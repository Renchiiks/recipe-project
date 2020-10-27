package com.example.recipeproject.converters;

import com.example.recipeproject.commands.NotesCommand;
import com.example.recipeproject.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "Notes";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(ID_VALUE);
        command.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}