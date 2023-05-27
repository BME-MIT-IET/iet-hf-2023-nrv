package com.nrv.unit.model.strategy;

import model.Virologist;
import model.map.Field;
import model.strategy.DefMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DefMoveTest {

    @Mock
    private Virologist virologist;

    @Mock
    private Field fromField;

    @Mock
    private Field toField;

    private DefMove defMove;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        defMove = new DefMove();
    }

    @Test
    void testMove() {
        defMove.move(virologist, fromField, toField);

        verify(fromField).removeVirologist(virologist);
        verify(toField).addVirologist(virologist);

        verify(virologist).decreaseActions();
    }
}

