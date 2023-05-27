package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Stun;
import model.strategy.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class StunTest {
    @Mock
    private Virologist virologist;
    private Stun stun = new Stun(1);

    @Test
    void test_applyStrategy() {
        stun.applyStrategy(virologist);

        verify(virologist, times(1)).setInjectedStr(any(NoInjected.class));
        verify(virologist, times(0)).setInjectedStr(any(DefInjected.class));

        verify(virologist, times(1)).setCollectStr(any(NoCollect.class));
        verify(virologist, times(0)).setCollectStr(any(DefCollect.class));

        verify(virologist, times(1)).setDropStr(any(NoDrop.class));
        verify(virologist, times(0)).setDropStr(any(DefDrop.class));

        verify(virologist, times(1)).setEquipStr(any(NoEquip.class));
        verify(virologist, times(0)).setEquipStr(any(DefEquip.class));

        verify(virologist, times(1)).setInjectStr(any(NoInject.class));
        verify(virologist, times(0)).setInjectStr(any(DefInject.class));

        verify(virologist, times(1)).setLearnStr(any(NoLearn.class));
        verify(virologist, times(0)).setLearnStr(any(DefLearn.class));

        verify(virologist, times(1)).setLootedStr(any(Looted.class));
        verify(virologist, times(0)).setLootedStr(any(DefLooted.class));

        verify(virologist, times(1)).setLootStr(any(NoLoot.class));
        verify(virologist, times(0)).setLootStr(any(DefLoot.class));

        verify(virologist, times(1)).setMoveStr(any(NoMove.class));
        verify(virologist, times(0)).setMoveStr(any(DefMove.class));
    }
}
