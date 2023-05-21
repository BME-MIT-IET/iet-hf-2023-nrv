package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.BlockCode;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BlockCodeTest {

    private BlockCode blockCode = new BlockCode();
    @Test
    public void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class, () -> blockCode.Create(virologist));
    }

    @Test
    public void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(blockCode.getAminoAcidPrice());
        virologist.AddNucleotide(blockCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> blockCode.Create(virologist));
    }
}
