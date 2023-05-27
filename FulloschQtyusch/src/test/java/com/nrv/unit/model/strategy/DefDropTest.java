package com.nrv.unit.model.strategy;

import model.Virologist;
import model.equipments.Equipment;
import model.map.Field;
import model.strategy.DefDrop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefDropTest {

    private DefDrop defDrop;
    private Virologist virologist;
    private Field field;
    private Equipment equipment;

    @BeforeEach
    void setUp() {
        defDrop = new DefDrop();
        virologist = mock(Virologist.class);
        field = mock(Field.class);
        equipment = mock(Equipment.class);
    }

    @Test
    void testDrop() {
        defDrop.drop(virologist, field, equipment);

        verify(equipment).disable(virologist);
        verify(field).drop(equipment);
        verify(virologist).reset();
        verify(virologist).decreaseActions();
    }
}
