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
        glove.ApplyStrategy(virologist);

        verify(virologist, times(1)).SetInjectedStr(glove);
    }

    @Test
    void test_injected() {
        Chorea chorea = new Chorea(1);
        glove.Injected(virologist, chorea);

        verify(virologist, times(0)).RemoveEquipment(glove);
        verify(virologist, times(0)).Reset();
        verify(virologist, times(0)).TargetedWith(chorea);
    }

    @Test
    void test_injectedDestroyed() {
        Chorea chorea = new Chorea(1);
        glove.Injected(virologist, chorea);
        glove.Injected(virologist, chorea);
        glove.Injected(virologist, chorea);
        glove.Injected(virologist, chorea);

        verify(virologist, times(1)).RemoveEquipment(glove);
        verify(virologist, times(1)).Reset();
        verify(virologist, times(1)).TargetedWith(chorea);
    }

    @Test
    void test_injectedBySomeone() {
        Chorea chorea = new Chorea(1);
        glove.Injected(byVirologist, virologist, chorea);

        verify(byVirologist, times(1)).TargetedWith(virologist, chorea);
        verify(virologist, times(0)).TargetedWith(byVirologist, chorea);
        verify(virologist, times(0)).RemoveEquipment(glove);
        verify(virologist, times(0)).Reset();
    }

    @Test
    void test_injectedBySomeoneDestroyed() {
        Chorea chorea = new Chorea(1);
        glove.Injected(byVirologist, virologist, chorea);
        glove.Injected(byVirologist, virologist, chorea);
        glove.Injected(byVirologist, virologist, chorea);
        glove.Injected(byVirologist, virologist, chorea);

        verify(byVirologist, times(3)).TargetedWith(virologist, chorea);

        verify(virologist, times(1)).RemoveEquipment(glove);
        verify(virologist, times(1)).Reset();
        verify(virologist, times(1)).TargetedWith(byVirologist, chorea);
    }
}
