package com.nrv.unit.model.strategy;

import model.Virologist;
import model.agents.Bear;
import model.map.Field;
import model.strategy.BearMove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class BearMoveTest {

    private BearMove bearMove;
    private Virologist virologist;
    private Virologist targetVirologist;
    private Field fromField;
    private Field toField;
    private List<Field> neighbours;

    @BeforeEach
    void setUp() {
        bearMove = new BearMove();
        virologist = mock(Virologist.class);
        targetVirologist = mock(Virologist.class);
        fromField = mock(Field.class);
        toField = mock(Field.class);
        neighbours = new ArrayList<>();
        neighbours.add(toField);
    }

    @Test
    void testMove() {
        when(toField.getVirologists()).thenReturn(List.of(targetVirologist));
        when(fromField.getNeighbours()).thenReturn(neighbours);

        bearMove.move(virologist, fromField, toField);

        verify(fromField).removeVirologist(virologist);
        verify(toField).addVirologist(virologist);
        verify(toField).destroyMaterial();
        verify(virologist).decreaseActions();
        verify(targetVirologist).targetedWith(any(Bear.class));
    }
}
