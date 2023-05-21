package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Forget;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ForgetTest {
    private Forget forget = new Forget(1);

    @Test
    public void test_apply() {
        Virologist virologist = new Virologist();
        forget.Apply(virologist);

        Assertions.assertEquals(0, virologist.getGeneticCodes().size());
    }
}
