package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Forget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ForgetTest {
    private Forget forget = new Forget(1);

    @Test
    void test_apply() {
        Virologist virologist = new Virologist();
        forget.Apply(virologist);

        Assertions.assertEquals(0, virologist.getGeneticCodes().size());
    }
}
