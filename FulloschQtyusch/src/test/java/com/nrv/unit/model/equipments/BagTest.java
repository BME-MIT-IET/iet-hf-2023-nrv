package com.nrv.unit.model.equipments;

import model.Virologist;
import model.equipments.Bag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BagTest {
    private Bag bag = new Bag();

    @Test
    void test_apply() {
        Virologist v = new Virologist();

        int limitBefore = v.getMaterialLimit();
        bag.apply(v);
        int limitAfter = v.getMaterialLimit();
        Assertions.assertEquals(bag.getDelta(), limitAfter - limitBefore);
    }

    @Test
    void test_disable() {
        Virologist v = new Virologist();

        int limitBefore = v.getMaterialLimit();
        bag.disable(v);
        int limitAfter = v.getMaterialLimit();
        Assertions.assertEquals(-bag.getDelta(), limitAfter - limitBefore);
    }
}
