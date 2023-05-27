package com.nrv.unit.model.strategy;

import model.Virologist;
import model.map.Field;
import model.strategy.DefEquip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefEquipTest {

    private DefEquip defEquip;
    private Virologist virologist;
    private Field field;

    @BeforeEach
    void setUp() {
        defEquip = new DefEquip();
        virologist = mock(Virologist.class);
        field = mock(Field.class);
    }

    @Test
    void testEquip() {
        defEquip.equip(virologist, field);

        verify(field).pickUpEquipment(virologist);
        verify(virologist).decreaseActions();
    }
}
