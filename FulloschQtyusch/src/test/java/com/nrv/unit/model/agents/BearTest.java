package com.nrv.unit.model.agents;

import model.Virologist;
import model.agents.Bear;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BearTest {
    private Bear bear = new Bear();

    @Test
    void test_applyStrategy() {
        Virologist virologist = new Virologist();
        virologist.setName("");
        bear.ApplyStrategy(virologist);

        Assertions.assertEquals(" (Bear)", virologist.getName());
    }
}
