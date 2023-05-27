package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.BlockCode;
import model.codes.GeneticCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlockCodeTest {

    private BlockCode blockCode = new BlockCode();
    @Test
    void test_createFailNucleotide() {
        Virologist virologist = new Virologist();
        virologist.addAminoAcid(blockCode.getAminoAcidPrice());
        Assertions.assertThrows(GeneticCode.GeneticCodeException.class,() -> blockCode.create(virologist));
    }

    @Test
    void test_createFailAmino() {
        Virologist virologist = new Virologist();
        virologist.addNucleotide(blockCode.getNucleotidePrice());
        Assertions.assertThrows(GeneticCode.GeneticCodeException.class,() -> blockCode.create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.addAminoAcid(blockCode.getAminoAcidPrice());
        virologist.addNucleotide(blockCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> blockCode.create(virologist));
    }
}
