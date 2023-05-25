package com.nrv.unit.model.equipments;

import model.Virologist;
import model.equipments.Axe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AxeTest {
    @Mock
    private Virologist attack;

    @Mock
    private Virologist target;

    private Axe axe = new Axe();

    @Test
    void test_attack() {
        axe.attack(attack, target);

        verify(attack, times(1)).decreaseActions();
        verify(target, times(1)).kill();
        verify(attack, times(1)).reset();
    }

    @Test
    void test_applyStrategy() {
        axe.applyStrategy(attack);

        verify(attack, times(1)).setAttackStr(axe);
    }
}
