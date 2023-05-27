package com.nrv.unit.model.strategy;

import model.Virologist;
import model.equipments.Equipment;
import model.map.Field;
import model.strategy.NoDrop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class NoDropTest {

    private Virologist virologist;
    private Field field;
    private Equipment equipment;
    private NoDrop noDrop;

    @BeforeEach
    void setUp() {
        virologist = mock(Virologist.class);
        field = mock(Field.class);
        equipment = mock(Equipment.class);
        noDrop = new NoDrop();
    }

    @Test
    void testDrop() {
        noDrop.drop(virologist, field, equipment);

        verify(virologist).addEquipment(equipment);
        verify(virologist).decreaseActions();
    }
}
