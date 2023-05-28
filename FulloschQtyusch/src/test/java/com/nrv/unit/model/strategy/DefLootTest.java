package com.nrv.unit.model.strategy;

import model.Virologist;
import model.strategy.DefLoot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DefLootTest {

    private DefLoot defLoot;
    private Virologist looter;
    private Virologist target;

    @BeforeEach
    void setUp() {
        defLoot = new DefLoot();
        looter = mock(Virologist.class);
        target = mock(Virologist.class);
    }

    @Test
    void testLootAmino() {
        defLoot.lootAmino(looter, target);

        verify(looter).decreaseActions();
        verify(target).stealAminoAcid(looter);
    }

    @Test
    void testLootNucleotide() {
        defLoot.lootNucleotide(looter, target);

        verify(looter).decreaseActions();
        verify(target).stealNucleotid(looter);
    }

    @Test
    void testLootEquipment() {
        defLoot.lootEquipment(looter, target);

        verify(looter).decreaseActions();
        verify(target).stealEquipment(looter);
    }
}
