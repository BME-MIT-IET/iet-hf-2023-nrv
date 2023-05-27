package com.nrv.unit.model.strategy;

import model.Virologist;
import model.map.Field;
import model.map.Material;
import model.strategy.DefCollect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefCollectTest {

    private DefCollect defCollect;
    private Virologist virologist;
    private Field field;
    private Material material;

    @BeforeEach
    void setUp() {
        defCollect = new DefCollect();
        virologist = mock(Virologist.class);
        field = mock(Field.class);
        material = Material.AMINO_ACID;
    }

    @Test
    void testCollect() {
        defCollect.collect(virologist, field, material);

        verify(field).collectMaterial(virologist, material);
        verify(virologist).decreaseActions();
    }
}
