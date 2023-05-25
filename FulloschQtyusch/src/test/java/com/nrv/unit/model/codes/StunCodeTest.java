package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.GeneticCode;
import model.codes.StunCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StunCodeTest {
    private StunCode stunCode = new StunCode();

    @Test
    void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(GeneticCode.GeneticCodeException.class,() -> stunCode.create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.addAminoAcid(stunCode.getAminoAcidPrice());
        virologist.addNucleotide(stunCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> stunCode.create(virologist));
    }
}
