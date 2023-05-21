package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Bear;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BearTest {
    private Bear bear = new Bear();

    @Test
    public void test_applyStrategy() {
        Virologist virologist = new Virologist();
        virologist.setName("");
        bear.ApplyStrategy(virologist);

        Assertions.assertEquals(" (Bear)", virologist.getName());
    }
}
