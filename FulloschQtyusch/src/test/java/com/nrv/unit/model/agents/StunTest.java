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
        stun.ApplyStrategy(virologist);

        verify(virologist, times(1)).SetInjectedStr(any(NoInjected.class));
        verify(virologist, times(0)).SetInjectedStr(any(DefInjected.class));

        verify(virologist, times(1)).SetCollectStr(any(NoCollect.class));
        verify(virologist, times(0)).SetCollectStr(any(DefCollect.class));

        verify(virologist, times(1)).SetDropStr(any(NoDrop.class));
        verify(virologist, times(0)).SetDropStr(any(DefDrop.class));

        verify(virologist, times(1)).SetEquipStr(any(NoEquip.class));
        verify(virologist, times(0)).SetEquipStr(any(DefEquip.class));

        verify(virologist, times(1)).SetInjectStr(any(NoInject.class));
        verify(virologist, times(0)).SetInjectStr(any(DefInject.class));

        verify(virologist, times(1)).SetLearnStr(any(NoLearn.class));
        verify(virologist, times(0)).SetLearnStr(any(DefLearn.class));

        verify(virologist, times(1)).SetLootedStr(any(Looted.class));
        verify(virologist, times(0)).SetLootedStr(any(DefLooted.class));

        verify(virologist, times(1)).SetLootStr(any(NoLoot.class));
        verify(virologist, times(0)).SetLootStr(any(DefLoot.class));

        verify(virologist, times(1)).SetMoveStr(any(NoMove.class));
        verify(virologist, times(0)).SetMoveStr(any(DefMove.class));
    }
}
