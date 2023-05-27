package com.nrv.unit.model.strategy;

import model.Virologist;
import model.agents.Agent;
import model.strategy.DefInjected;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefInjectedTest {

    private DefInjected defInjected;
    private Virologist virologist;
    private Agent agent;

    @BeforeEach
    void setUp() {
        defInjected = new DefInjected();
        virologist = mock(Virologist.class);
        agent = mock(Agent.class);
    }

    @Test
    void testInjected() {
        defInjected.injected(virologist, agent);

        verify(agent).apply(virologist);
        verify(virologist).addAgent(agent);
        verify(agent).applyStrategy(virologist);
    }

    @Test
    void testInjectedWithVirologist() {
        Virologist by = mock(Virologist.class);

        defInjected.injected(by, virologist, agent);

        verify(virologist).addAgent(agent);
        verify(agent).applyStrategy(virologist);
    }
}
