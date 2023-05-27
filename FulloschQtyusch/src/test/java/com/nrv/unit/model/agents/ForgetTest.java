package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Forget;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ForgetTest {
    private Forget forget = new Forget(1);

    @Test
    void test_apply() {
        Virologist virologist = new Virologist();
        forget.apply(virologist);

        Assertions.assertEquals(0, virologist.getGeneticCodes().size());
    }
}
