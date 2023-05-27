package com.nrv.unit.model.strategy;

import model.Virologist;
import model.map.Field;
import model.strategy.DefLearn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefLearnTest {

    private DefLearn defLearn;
    private Virologist virologist;
    private Field field;

    @BeforeEach
    void setUp() {
        defLearn = new DefLearn();
        virologist = mock(Virologist.class);
        field = mock(Field.class);
    }

    @Test
    void testLearn() {
        defLearn.learn(virologist, field);

        verify(field).learnGeneticCode(virologist);
        verify(virologist).decreaseActions();
    }
}
