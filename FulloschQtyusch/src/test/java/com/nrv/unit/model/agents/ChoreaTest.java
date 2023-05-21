package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Chorea;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
class ChoreaTest {

    @Mock
    private Virologist virologist;

    private Chorea chorea = new Chorea(1);

    @Test
    void test_apply() {
        chorea.Apply(virologist);
        verify(virologist, times(3)).Move();
    }
}
