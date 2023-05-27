package com.nrv.unit.model.strategy;

import model.Virologist;
import model.codes.GeneticCode;
import model.strategy.DefInject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefInjectTest {

    private DefInject defInject;
    private Virologist virologist;
    private Virologist target;
    private GeneticCode geneticCode;

    @BeforeEach
    void setUp() {
        defInject = new DefInject();
        virologist = mock(Virologist.class);
        target = mock(Virologist.class);
        geneticCode = mock(GeneticCode.class);
    }

    @Test
    void testInject() {
        defInject.inject(virologist, target, geneticCode);

        verify(virologist).decreaseActions();
        verify(target).targetedWith(virologist, geneticCode.create(virologist));
    }
}
