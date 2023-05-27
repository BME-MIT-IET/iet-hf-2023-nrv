package com.nrv.unit.model.equipments;

import model.Virologist;
import model.equipments.Cloak;
import model.strategy.NoInjected;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CloakTest {
    @Mock
    private Random random;
    @Mock
    private Virologist virologist;
    private Cloak cloak;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cloak = new Cloak(random);
    }
    @Test
    void test_applyStrategySuccess() {
        when(random.nextDouble()).thenReturn(0.5);
        cloak.applyStrategy(virologist);
        verify(virologist, times(1)).setInjectedStr(any(NoInjected.class));
    }

    @Test
    void test_applyStrategyFailure() {
        when(random.nextDouble()).thenReturn(1.0);
        cloak.applyStrategy(virologist);
        verify(virologist, times(0)).setInjectedStr(any(NoInjected.class));
    }
}
