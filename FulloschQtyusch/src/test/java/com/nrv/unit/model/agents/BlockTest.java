package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Block;
import model.strategy.DefInjected;
import model.strategy.NoInjected;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
class BlockTest {

    @Mock
    private Virologist virologist;

    private Block block = new Block(1);
    @Test
    void test_applyStrategy() {
        block.ApplyStrategy(virologist);
        verify(virologist, times(1)).SetInjectedStr(any(NoInjected.class));
        verify(virologist, times(0)).SetInjectedStr(any(DefInjected.class));
    }

    @Test
    void test_apply() {
        block.Apply(virologist);
        verify(virologist, times(1)).RemoveAgents();
    }
}
