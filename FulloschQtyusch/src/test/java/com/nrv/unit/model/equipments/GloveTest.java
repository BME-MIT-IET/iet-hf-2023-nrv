package com.nrv.unit.model.equipments;

import model.Virologist;
import model.agents.Chorea;
import model.equipments.Glove;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GloveTest {
    @Mock
    private Virologist virologist;
    @Mock
    private Virologist byVirologist;

    private Glove glove;

    @BeforeEach
    void setUp() {
        glove = new Glove();
    }

    @Test
    void test_applyStrategy() {
        glove.applyStrategy(virologist);

        verify(virologist, times(1)).setInjectedStr(glove);
    }

    @Test
    void test_injected() {
        Chorea chorea = new Chorea(1);
        glove.injected(virologist, chorea);

        verify(virologist, times(0)).removeEquipment(glove);
        verify(virologist, times(0)).reset();
        verify(virologist, times(0)).targetedWith(chorea);
    }

    @Test
    void test_injectedDestroyed() {
        Chorea chorea = new Chorea(1);
        glove.injected(virologist, chorea);
        glove.injected(virologist, chorea);
        glove.injected(virologist, chorea);
        glove.injected(virologist, chorea);

        verify(virologist, times(1)).removeEquipment(glove);
        verify(virologist, times(1)).reset();
        verify(virologist, times(1)).targetedWith(chorea);
    }

    @Test
    void test_injectedBySomeone() {
        Chorea chorea = new Chorea(1);
        glove.injected(byVirologist, virologist, chorea);

        verify(byVirologist, times(1)).targetedWith(virologist, chorea);
        verify(virologist, times(0)).targetedWith(byVirologist, chorea);
        verify(virologist, times(0)).removeEquipment(glove);
        verify(virologist, times(0)).reset();
    }

    @Test
    void test_injectedBySomeoneDestroyed() {
        Chorea chorea = new Chorea(1);
        glove.injected(byVirologist, virologist, chorea);
        glove.injected(byVirologist, virologist, chorea);
        glove.injected(byVirologist, virologist, chorea);
        glove.injected(byVirologist, virologist, chorea);

        verify(byVirologist, times(3)).targetedWith(virologist, chorea);

        verify(virologist, times(1)).removeEquipment(glove);
        verify(virologist, times(1)).reset();
        verify(virologist, times(1)).targetedWith(byVirologist, chorea);
    }
}
