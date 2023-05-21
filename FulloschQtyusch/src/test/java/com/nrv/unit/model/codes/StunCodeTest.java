package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.StunCode;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StunCodeTest {
    private StunCode stunCode = new StunCode();

    @Test
    public void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class,() -> stunCode.Create(virologist));
    }

    @Test
    public void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(stunCode.getAminoAcidPrice());
        virologist.AddNucleotide(stunCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> stunCode.Create(virologist));
    }
}
