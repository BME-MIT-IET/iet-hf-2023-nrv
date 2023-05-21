package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.StunCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class StunCodeTest {
    private StunCode stunCode = new StunCode();

    @Test
    void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class,() -> stunCode.Create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(stunCode.getAminoAcidPrice());
        virologist.AddNucleotide(stunCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> stunCode.Create(virologist));
    }
}
