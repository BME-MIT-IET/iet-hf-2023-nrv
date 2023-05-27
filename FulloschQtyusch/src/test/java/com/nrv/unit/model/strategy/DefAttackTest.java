package com.nrv.unit.model.strategy;

import model.Virologist;
import model.strategy.DefAttack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefAttackTest {

    private DefAttack defAttack;
    private Virologist attacker;
    private Virologist target;

    @BeforeEach
    void setUp() {
        defAttack = new DefAttack();
        attacker = mock(Virologist.class);
        target = mock(Virologist.class);
    }

    @Test
    void testAttack() {
        defAttack.attack(attacker, target);

        verify(attacker).decreaseActions();
        verifyNoMoreInteractions(target);
    }
}

