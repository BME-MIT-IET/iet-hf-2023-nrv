package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.BlockCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlockCodeTest {

    private BlockCode blockCode = new BlockCode();
    @Test
    void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class, () -> blockCode.Create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(blockCode.getAminoAcidPrice());
        virologist.AddNucleotide(blockCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> blockCode.Create(virologist));
    }
}