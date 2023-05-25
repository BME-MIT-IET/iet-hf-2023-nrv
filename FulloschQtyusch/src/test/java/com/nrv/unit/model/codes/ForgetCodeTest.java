package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.ForgetCode;
import model.codes.GeneticCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ForgetCodeTest {
    private ForgetCode forgetCode = new ForgetCode();

    @Test
    void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(GeneticCode.GeneticCodeException.class,() -> forgetCode.create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.addAminoAcid(forgetCode.getAminoAcidPrice());
        virologist.addNucleotide(forgetCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> forgetCode.create(virologist));
    }
}
